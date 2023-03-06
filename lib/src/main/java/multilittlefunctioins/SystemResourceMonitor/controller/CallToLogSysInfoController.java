package multilittlefunctioins.SystemResourceMonitor.controller;

import java.net.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/callsysinfo")
public class CallToLogSysInfoController {
	
	@GetMapping()
	public HttpResponse callSysInfo() {
		
		return null;
	}
	
	
}
