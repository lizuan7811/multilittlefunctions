package little_func_swagifs.service.impl;

import org.springframework.stereotype.Service;

import little_func_swagifs.service.TodoService;
@Service
public class TodoServiceImpl implements TodoService{

	public void printHello() {
		System.out.println("Hello TodoService");
	}
	
}
