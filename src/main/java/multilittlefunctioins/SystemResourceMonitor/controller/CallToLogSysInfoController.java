package multilittlefunctioins.SystemResourceMonitor.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import multilittlefunctioins.SystemResourceMonitor.service.CallToLogSysInfoService;
@RestController
@RequestMapping("/calltolog")
public class CallToLogSysInfoController {
	private final CallToLogSysInfoService callToLogSysInfoService;

	@Autowired
	public CallToLogSysInfoController(CallToLogSysInfoService callToLogSysInfoService) {
		this.callToLogSysInfoService = callToLogSysInfoService;
	}

	@GetMapping(value = "/callSysCpu", produces = MediaType.APPLICATION_JSON_VALUE)
	public String callSysCpu() {
		String result = StringUtils.EMPTY;

		try {
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
			result = callToLogSysInfoService.readSysMemInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

//		return null;
	}

}