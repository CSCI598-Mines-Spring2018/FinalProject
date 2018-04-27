public class SIM {
    public static void main ( String[] args )
    {
        //TODO: uncomment these when ready
        if( args.length < 1 ) {
            System.out.println("Not enough command arguments!");
            System.exit(1);
        }
		/*else if(args.length > 2) {
			System.out.println("Too many command arguments!");
			System.exit(1);
		}*/

        RoboticsSim sim = new RoboticsSim(args);
        String result = sim.run();
        System.out.println(result);
    }
}