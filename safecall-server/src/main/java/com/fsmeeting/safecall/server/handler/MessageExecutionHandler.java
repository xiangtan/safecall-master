package com.fsmeeting.safecall.server.handler;
import java.util.concurrent.Executor;  

import org.jboss.netty.handler.execution.ExecutionHandler;  
  
// 提供一个线程池  
public class MessageExecutionHandler extends ExecutionHandler{  
  
    public MessageExecutionHandler(Executor executor) {  
        super(executor);  
    }  
}  