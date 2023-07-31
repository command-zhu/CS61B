public class NBody {
    public static String imageToDraw = "images/starfield.jpg";
    public static String sun = "images/sun.gif";
    public static String mercury = "images/mercury.gif";
    public static String venus = "images/venus.gif";
    public static String earth = "images/earth.gif";
    public static String mars = "images/mars.gif";
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        //cosmos r
        Planet[] pp = NBody.readPlanets(filename);
        double r = NBody.readRadius(filename);

//        NBody.drawinit(r);
//        NBody.drawstar(pp);
        double t = 0;
        int planetsnum = 4;
        while(t < T) {
            double[] xForces = new double[planetsnum];
            double[] yForces = new double[planetsnum];
            for(int i = 0; i < planetsnum; i ++) {
                xForces[i] = pp[i].calcNetForceExertedByX(pp);
                yForces[i] = pp[i].calcNetForceExertedByY(pp);
            }
            for(int i = 0; i < planetsnum; i ++) {
                pp[i].update(dt, xForces[i], yForces[i]);
            }
            NBody.drawinit(r);
            NBody.drawstar(pp);
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", pp.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < pp.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    pp[i].xxPos, pp[i].yyPos, pp[i].xxVel,
                    pp[i].yyVel, pp[i].mass, pp[i].imgFileName);
        }


    }

    public static void drawinit(double r) {
        //绘制静态
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1 * r, 1 * r);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
    }
    public static void drawstar(Planet[] pp) {
        StdDraw.picture(pp[0].xxPos, pp[0].yyPos, earth);
        StdDraw.picture(pp[1].xxPos, pp[1].yyPos, mars);
        StdDraw.picture(pp[2].xxPos, pp[2].yyPos, mercury);
        StdDraw.picture(pp[3].xxPos, pp[3].yyPos, sun);
        StdDraw.picture(pp[4].xxPos, pp[4].yyPos, venus);
        StdDraw.show();
    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

public static Planet[] readPlanets(String filepath) {
    In in = new In(filepath);
    int num = 5;
    Planet[] planets = new Planet[num];
    int firstItemInFile = in.readInt();
    double secondItemInFile = in.readDouble();
    for (int i = 0; i < num; i++) {
        //这句话很重要！需要先实例化元素。类变成对象的过程就是类的实例化
        //需要在Planet.java中写构造函数
        planets[i] = new Planet(); // 实例化每个元素
        planets[i].xxPos = in.readDouble();
        //System.out.println(planets[i].xxPos);
        planets[i].yyPos = in.readDouble();
        planets[i].xxVel = in.readDouble();
        planets[i].yyVel = in.readDouble();
        planets[i].mass = in.readDouble();
        planets[i].imgFileName = in.readString();
    }
    return planets;
}
}
