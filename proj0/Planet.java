public class Planet {
  public double xxPos = 1;
  public double yyPos = 2;
  public double xxVel = 3; // velocity in the x direction
  public double yyVel = 4; // velocity in the y direction
  public double mass = 5;
  public String imgFileName = "jupiter.gif";
  private static final double G = 6.67e-11; // gravitational constant

  public Planet(double xP, double yP, double xV,
      double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p1) {
    double x2 = (this.xxPos - p1.xxPos) * (this.xxPos - p1.xxPos);
    double y2 = (this.yyPos - p1.yyPos) * (this.yyPos - p1.yyPos);
    return Math.sqrt(x2 + y2);
  }

  public double calcForceExertedBy(Planet p) {
    double r = this.calcDistance(p);
    double f = (G * this.mass * p.mass) / (r * r);
    return f;
  }
  public double calcForceExertedByX(Planet p) {
    double f = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dx = p.xxPos - this.xxPos;
    //if(dx < 0)  dx = -dx;
    double fx = f * dx / r;
    return  fx;
  }

  public double calcForceExertedByY(Planet p) {
    double f = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dy = p.yyPos - this.yyPos;
    //if(dy < 0) dy = -dy;
    double fy = f * dy / r;
    return  fy;
  }

  public double calcNetForceExertedByX(Planet[] all) {
    double fxnet = 0;
    for(Planet p : all) {
      if(this.equals(p))  continue;
      double f = this.calcForceExertedByX(p);
      //if(this.xxPos - p.xxPos > 0)  f = -f;
      fxnet += f;
    }
    return fxnet;
  }

  public double calcNetForceExertedByY(Planet[] all) {
    double fynet = 0;
    for(Planet p : all) {
      if(this.equals(p))  continue;
      double f = this.calcForceExertedByY(p);
      //if(this.yyPos - p.yyPos > 0)  f = -f;
      fynet += f;
    }
    return fynet;
  }

  public void update(double dt, double fX, double fY) {
    double ax = fX / this.mass;
    double ay = fY / this.mass;
    this.xxVel = this.xxVel + dt * ax;
    this.yyVel = this.yyVel + dt * ay;
    this.xxPos = this.xxPos + dt * this.xxVel;
    this.yyPos = this.yyPos + dt * this.yyVel;
  }

  public void draw() {

  }

}