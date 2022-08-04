public class NBody {
    /** return the radius of the universe */
    public static double readRadius(String file_add) {
        In in = new In(file_add);
        int planet_number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // **find all the planets in the file and put them into planet */
    public static Planet[] readPlanets(String file_add) {
        In in = new In(file_add);
        int planet_number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planet_number];
        for (int i = 0; i < planet_number; i++) {
            double x_pos = in.readDouble();
            double y_pos = in.readDouble();
            double x_vel = in.readDouble();
            double y_vel = in.readDouble();
            double mass = in.readDouble();
            String gif = in.readString();
            planets[i] = new Planet(x_pos, y_pos, x_vel, y_vel, mass, gif);
        }
        return planets;
    }
    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double universe_radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);
        String background_img="images/starfield.jpg";
        StdDraw.setScale(-4e+11, 4e+11);
        StdDraw.picture(-universe_radius, universe_radius,background_img);//put the background in the middle
        StdDraw.show();//show the background
        for(Planet p:planets){
            p.draw();
        }

    }
}