package com.gwg.demo.mq.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 
 */
//for example
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMessage {
    
	private int id;
    
    //学生姓名
    private String name;
    
    //出生日期
    private Date birthday;
    
}
