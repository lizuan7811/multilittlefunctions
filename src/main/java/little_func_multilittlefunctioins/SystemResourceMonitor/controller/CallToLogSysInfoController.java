package little_func_multilittlefunctioins.SystemResourceMonitor.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import little_func_monitorsysinfoutil.multiportdeal.WebPortConfig;
import little_func_multilittlefunctioins.SystemResourceMonitor.service.CallToLogSysInfoService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/app/v1/callsysinfo")
@Slf4j
public class CallToLogSysInfoController {
	private final CallToLogSysInfoService callToLogSysInfoService;

	private final WebPortConfig webPortConfig;
	
	@Autowired
	public CallToLogSysInfoController(CallToLogSysInfoService callToLogSysInfoService,WebPortConfig webPortConfig) {
		this.callToLogSysInfoService = callToLogSysInfoService;
		this.webPortConfig=webPortConfig;
	}

	@GetMapping(value = "/callSysCpu", produces = MediaType.APPLICATION_JSON_VALUE)
	public String callSysCpu() {
		String result = StringUtils.EMPTY;

		try {
			log.debug("Port: {}",webPortConfig.getServerContext().get(0).getPort());
			result = callToLogSysInfoService.readSysCpuInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
//		return null;
	}

	@GetMapping(value = "/callSysMem", produces = MediaType.APPLICATION_JSON_VALUE)
	public String callSysMem() {
		String result = StringUtils.EMPTY;

		try {
			log.debug("Port: {}",webPortConfig.getServerContext().get(0).getPort());
			result = callToLogSysInfoService.readSysMemInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

//		return null;
	}

}