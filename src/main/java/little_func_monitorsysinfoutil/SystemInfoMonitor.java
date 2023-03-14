package little_func_monitorsysinfoutil;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@Slf4j
@Component
public class SystemInfoMonitor {

	private static long[] prevTicks = null;
	private static long[] ticks = null;

	private SystemInfo getSystemInfo() {
		return new SystemInfo();
	}

	public String readSystemCpuInfo() {

		CentralProcessor centralprocess = getSystemInfo().getHardware().getProcessor();
		if (prevTicks == null) {
			prevTicks = centralprocess.getSystemCpuLoadTicks();
		}
		ticks = centralprocess.getSystemCpuLoadTicks();

		long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
				- prevTicks[CentralProcessor.TickType.NICE.getIndex()];
		log.debug("CPU nice: {}", nice);
		long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
		log.debug("CPU nice: {}", irq);
		long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
		log.debug("CPU softirq: {}", softirq);
		long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
				- prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
		log.debug("CPU steal: {}", steal);
		long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
				- prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
		log.debug("CPU cSys: {}", cSys);
		long user = ticks[CentralProcessor.TickType.USER.getIndex()]
				- prevTicks[CentralProcessor.TickType.USER.getIndex()];
		log.debug("CPU user: {}", user);
		long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
				- prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
		log.debug("CPU iowait: {}", iowait);
		long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
				- prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
		log.debug("CPU idle: {}", idle);

		long totalCpu = idle + iowait + user + cSys + steal + softirq + irq + nice;
		System.out.println(totalCpu);
		log.debug("CPU totalCpu: {}", totalCpu);
		String result = String.format("CPU 總量: %s , CPU使用率: %s", centralprocess.getLogicalProcessorCount(),
				new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
		log.debug("CPU Result: {}", result);
		this.prevTicks = ticks;

		sleepOneSecond();
		return result;
	}

	public String readSystemMemInfo() {
		GlobalMemory globalMem = getSystemInfo().getHardware().getMemory();
		long acaliableByte = globalMem.getAvailable();
		long totalByte = globalMem.getTotal();
		String result = String.format("MEM總量: %s , MEM使用率: %s", formatByte(totalByte),
				new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
		log.debug("MEM Result: {}", result);
		sleepOneSecond();
		return result;

	}

	private String formatByte(long byteNumber) {

		String result = StringUtils.EMPTY;
		double FORMAT = 1024.0;
		double kbNubmer = byteNumber / FORMAT;
		if (kbNubmer < FORMAT) {
			result = new DecimalFormat("#.##KB").format(kbNubmer);
		}
		double mbNubmer = kbNubmer / FORMAT;
		;
		if (mbNubmer < FORMAT) {
			result = new DecimalFormat("#.##MB").format(mbNubmer);
		}
		double gbNubmer = mbNubmer / FORMAT;
		;
		if (gbNubmer < FORMAT) {
			result = new DecimalFormat("#.##GB").format(gbNubmer);
		}
		double tbNubmer = gbNubmer / FORMAT;
		;
		if (tbNubmer < FORMAT) {
			result = new DecimalFormat("#.##TB").format(tbNubmer);
		}
		return result;
	}

	private void sleepOneSecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
