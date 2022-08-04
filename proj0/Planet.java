public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName="";

    /** first way to initiate a planet */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName += img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * to calculate the distance between this planet and the
     * given planet
     */
    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    /** calculate the force exterted by the planet p on this planet */
    public double calcForceExertedBy(Planet p) {
        return (6.67e-11 * mass * p.mass) / (calcDistance(p) * calcDistance(p));// 6.67e-11 here stands for G
    }

    /** calculate the force exert by p on x direction */
    public double calcForceExertedByX(Planet p) {
        double x_force = calcForceExertedBy(p) * (p.xxPos-xxPos) / calcDistance(p);
        return x_force;
    }

    /** calculate the force exert by p on y direction */
    public double calcForceExertedByY(Planet p) {
        double y_force = calcForceExertedBy(p) * (p.yyPos-yyPos) / calcDistance(p);
        return y_force;
    }

    /** gives the net value exerted by the planet */
    public double calcNetForceExertedByX(Planet[] planets) {
        double sum = 0;// the sum value of the function
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;// don't calculate the force exert by the planet itself
            }
            sum += calcForceExertedByX(planet);
        }
        return sum;
    }

    /** gives the net value exerted by the planet */
    public double calcNetForceExertedByY(Planet[] planets) {
        double sum = 0;// the sum value of the function
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;// don't calculate the force exert by the planet itself
            }
            sum += calcForceExertedByY(planet);
        }
        return sum;
    }

    /** update the location and the speed of the planet */
    public void update(double dt, double fX, double fY) {
        double a_x = fX / mass;
        double a_y = fY / mass;
        xxVel += a_x * dt;
        yyVel += a_y * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos,imgFileName);
        StdDraw.show();//show the background
    }
}