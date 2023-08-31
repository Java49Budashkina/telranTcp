package telran.interview;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

record Person(Integer id, String name) {}

public class StreamTasks {
	
	
	
	static public void displayOccurences(String strings[]) {
		Arrays.stream(strings).collect(Collectors.groupingBy(s->s, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> {
			int res = Long.compare(e2.getValue(), e1.getValue());
			return res != 0 ? res : e1.getKey().compareTo(e2.getKey());
		})
		.forEach( e -> System.out.printf("%s -> %s \n", e.getKey(), e.getValue()));
		}
	static public long sumGroups(int [][] groups) {
		return Arrays.stream(groups).flatMapToLong(g -> Arrays.stream(g).asLongStream())
				.sum();
	}
	static public void displayOddEvenGrouping(int nNumbers) {
		new Random().ints(nNumbers, 0,100).boxed()
		.collect(Collectors.groupingBy(n -> n%2 != 0 ? "odd" : "even",
				Collectors.averagingInt(x -> x)
			//	Collectors.summingLong(x -> x)
				//Collectors.counting()
				))
		.forEach((k,v) -> System.out.printf("%s -> %s \n", k, v));
	}
	
	static public void displayStatisticNumbers(int nNumbers) {
		new Random().ints(nNumbers, 0,10000)
			.mapToObj(Integer::toString)
			.flatMap(s -> Arrays.stream(s.split("")))
			.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(),  e1.getValue()))
			.forEach(e -> System.out.printf("%s : %s \n", e.getKey(), e.getValue()));
	}
	
	static public void displayStatisticNumbers1(int nNumbers) {
		new Random().ints(nNumbers, 0,Integer.MAX_VALUE)
			.flatMap(num -> Integer.toString(num).chars())
			.boxed().collect(Collectors.groupingBy(d -> d, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e1.getKey(),  e2.getKey()))
			.forEach(e -> System.out.printf("%c : %s \n", e.getKey(), e.getValue()));
	}
	
	 public static Map<Integer, Person> getRandomRersons(int numPersons){
		 return new Random().ints(1000, 2001).distinct()
				 .limit(numPersons)
				.mapToObj(id -> new Person(id, "name" + id))
		//		.collect(Collectors.toMap(p -> p.id(), p  -> p));
		 .collect(Collectors.toMap(p -> p.id(), p  -> p, (p,u) -> p, TreeMap::new));
		 
		
	}
}


