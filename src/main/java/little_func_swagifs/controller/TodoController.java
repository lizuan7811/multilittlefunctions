package little_func_swagifs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import little_func_swagifs.service.TodoService;

@Api(tags="Todo List 測試Swagger")
@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	TodoService todoService;
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@ApiOperation("取得所有代辦事項列表")
	@ApiResponses({
		@ApiResponse(code=401,message="沒有權限"),
		@ApiResponse(code=404,message="找不到資料")
	})
	@GetMapping(value="/apiTodo",produces=MediaType.APPLICATION_JSON_VALUE)
	public String apiTodo() {
//		ServletResponse rs=new HttpServletResponseWrapper(httpServletResponse);
//		
//		todoService.printHello();
		return "HELLO";
	}
	
}
