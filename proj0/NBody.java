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
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universe_radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        String background_img = "images/starfield.jpg";
        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.picture(0, 0, background_img);// put the background in the middle
        StdDraw.show();// show the background
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();// to prevent flickering in animation
        double time = 0;// remember the whole time this has been running
        while (time < T) {
            double[] xforces = new double[planets.length];
            double[] yforces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xforces[i] = planets[i].calcNetForceExertedByX(planets);
                yforces[i] = planets[i].calcNetForceExertedByY(planets);
            } // calcuate all the forces abd save them
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xforces[i], yforces[i]);// update the forces of planets
            }
            StdDraw.picture(0, 0, background_img);// put the background in the middle
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();// show the picture
            StdDraw.pause(10);// pause the program for 10 mileseconds
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }//print out the final state of the planet
    }
}