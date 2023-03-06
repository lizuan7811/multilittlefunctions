package multilittlefunctioins.SystemResourceMonitor.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import monitorsysinfoutil.SystemInfoMonitor;
import multilittlefunctioins.SystemResourceMonitor.service.CallToLogSysInfoService;

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
