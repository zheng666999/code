package test;
import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
    	Test obj = new Test();
        List<String> res = null;
		try {
			res = obj.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Set<String> crclNos = new HashSet<>();
        for (String line : res) {
            String[] firstSplit = line.split("、");
            String[] secSplit = firstSplit[0].split("贵司持有的编号为");
            crclNos.add(" select '"+secSplit[1]+"' from dual ");
        }
        String wholeStr = crclNos.stream().collect(Collectors.joining("union all"));
        System.out.println(wholeStr);
    }

    public List<String> test() throws Exception {
      List<String> res = new LinkedList<>();
      File file  = new File("C:\\Users\\Administrator\\Desktop\\sms\\23hao.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      String line = reader.readLine();
      while (line!= null){
        res.add(line);
        line = reader.readLine();
      }
      return res;
   }
}