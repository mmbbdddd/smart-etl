package cn.hz.ddbm.setl.domain;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ActionTest {


    @Test
    public void componentLineResolver(){
        String expr = "#sh mkdir /data/isms65/etl/${yyyyMMdd}/${taskId}";
//        String expr = "#sh ~/bin/load_data.sh   /data/isms65/etl/${yyyyMMdd}/abc.csv dwd_tf_fundinfo";

        String pattern = "(#.*?)(\\s{1}.*?)(\\s{1}.*)";

        Matcher matcher = Pattern.compile(pattern).matcher(expr);

        if(matcher.find()){
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }

    }
}