package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.fest.assertions.api.Assertions.assertThat;
public class LibraryTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    public void testAddStudentMethod() {
        Library classUnderTest = new Library();
        assertTrue("AddStudent should return 'true'", classUnderTest.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80"));
        assertThat(systemOut().endsWith("学生张三的成绩被添加\n")).isTrue();
        assertFalse("AddStudent should return 'false'", classUnderTest.addStudent("张三,1语文:95,英语:80,编程:80"));
        assertThat(systemOut().endsWith("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n")).isTrue();
    }
    @Test
    public void testPrintMethod(){
        Library classUnderTest = new Library();
        classUnderTest.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80");
        classUnderTest.addStudent("李四,2,数学:85,语文:80,英语:70,编程:90");
        assertFalse("AddStudent should return 'false'", classUnderTest.print("1,,"));
        assertThat(systemOut().endsWith("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n")).isTrue();
        assertTrue("AddStudent should return 'true'", classUnderTest.print("1,2"));
        String expectedOut="成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75.0|95.0|80.0|80.0|82.5|330.0\n" +
                "李四|85.0|80.0|70.0|90.0|81.25|325.0\n" +
                "========================\n" +
                "全班总分平均数：81.875\n" +
                "全班总分中位数：81.875\n";
        assertThat(systemOut().endsWith(expectedOut)).isTrue();

    }
//    @Test
//    public void testMockClass() throws Exception {
//        // you can mock concrete classes, not only interfaces
//        LinkedList mockedList = mock(LinkedList.class);
//
//        // stubbing appears before the actual execution
//        String value = "first";
//        when(mockedList.get(0)).thenReturn(value);
//
//        assertEquals(mockedList.get(0), value);
//
//    }

    private String systemOut() {
        return outContent.toString();
    }
}
