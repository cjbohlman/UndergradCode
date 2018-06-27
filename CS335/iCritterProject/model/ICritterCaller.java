package model;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class ICritterCaller {

	public static void main(String[] args) {
		
		// Create several ICriters and add interests to each
		Owner owner = new Owner("Dick", "Dick");
		ICritter spot = new ICritterDog("Spot", owner);
		spot.addInterest("Anime"); 								// 1
		spot.addInterest("Nintendo");							// 2
		spot.addInterest("Music");								// 3
		spot.addInterest("RPG");								// 4
		spot.addInterest("Games");								// 5
		
		ICritter rover = new ICritterDog("Rover", owner);
		rover.addInterest("Books");								// 6
		rover.addInterest("Buttons");							// 7
		rover.addInterest("Comedy");							// 8
		rover.addInterest("Comics");							// 9
		rover.addInterest("Manga");								// 10
		
		ICritter felix = new ICritterCat("Felix", owner);		//0111 00010 00010
		felix.addInterest("Garlic");							// 11
		felix.addInterest("Nintendo");							// 2
		felix.addInterest("Buttons");							// 7
		felix.addInterest("Statistics");						// 12
		felix.addInterest("Science");							// 13
		
		ICritter socks = new ICritterCat("Socks", owner);
		socks.addInterest("Canaries");							// 14				
		socks.addInterest("Dogs");								// 15
		socks.addInterest("Music");								// 3
		socks.addInterest("Games");								// 5
		socks.addInterest("Books");								// 6
		socks.addInterest("Vampires");							// 16
		
		ICritter zbignew = new ICritterPenguin("Zbignew", owner);
		zbignew.addInterest("Games");							// 5
		zbignew.addInterest("Music");							// 3
		zbignew.addInterest("Anime");							// 1
		zbignew.addInterest("Canaries");						// 14
		
		ICritter jermaine = new ICritterPenguin("Jermaine", owner);
		jermaine.addInterest("Science");						// 13
		jermaine.addInterest("Buttons");						// 7
		jermaine.addInterest("Garlic");							// 11
		jermaine.addInterest("Games");							// 5
		jermaine.addInterest("Manga");							// 10
		
		// Create ICritter world, populate it, and throw a jamboree!
		ICritterWorld icw = new ICritterWorld();
		icw.addICritter(spot);
		icw.addICritter(rover);
		icw.addICritter(felix);
		icw.addICritter(socks);
		icw.addICritter(zbignew);
		icw.addICritter(jermaine);
		
		icw.runJamboree();
		
		/** 
		 * Prints for each ICritter in ICritterWorld:
		 * <Name>
		 * Interest(s):
		 * <interest>
		 * 
		 * Friend(s):
		 * <name>\t<class>
		 * ------------------------------
		 */
		List<ICritter> icList = icw.listICritters();
		System.out.println();
		for (ICritter critter: icList) {
			System.out.println(critter.getName());
			String formatted = String.format("%064d", new BigInteger(Long.toBinaryString(critter.getKeyMap())));
			System.out.println(formatted);
			System.out.println("Interest(s):");
			Set<String> icInterests = critter.listInterests();
			for (String interest: icInterests) {
				System.out.println(interest);
			}
			System.out.println();
			System.out.println("Friend(s):");
			List<ICritter> icFriends = critter.getFriends();
			for (ICritter friendo: icFriends) {
				System.out.println(friendo.getName()+'\t'+friendo.getClass().getSimpleName());
			}			
			System.out.println("------------------------------");
		}
		
	}

}
