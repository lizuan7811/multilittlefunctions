package little_func_multilittlefunctioins.SystemResourceMonitor.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import little_func_monitorsysinfoutil.SystemInfoMonitor;
import little_func_multilittlefunctioins.SystemResourceMonitor.service.CallToLogSysInfoService;


@Service
public class CallToLogSysInfoServiceImpl implements CallToLogSysInfoService {

	@Autowired
	private SystemInfoMonitor systemInfoMonitor;
	
	@Override
	public String readSysCpuInfo() {
		return systemInfoMonitor.readSystemCpuInfo();
	}

	@Override
	public String readSysMemInfo() {
		return systemInfoMonitor.readSystemMemInfo();
	}
	
	

}
