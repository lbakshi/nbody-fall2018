
public class Body {
	private double myXVel;
	private double myXPos;
	private double myYPos;
	private double myYVel;
	private double myMass;
	private String myFileName;
	public Body(double x, double y, double xv, double yv, double mass, String filename) {
		myXVel=xv;
		myYVel=yv;
		myMass=mass;
		myXPos=x;
		myYPos=y;
		myFileName=filename;
	}
	public Body(Body b) {
		this.myXVel=b.myXVel;
		this.myYVel=b.myYVel;
		this.myMass=b.myMass;
		this.myXPos=b.myXPos;
		this.myYPos=b.myYPos;
		this.myFileName=b.myFileName;
		
	}
	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow((this.myXPos-b.myXPos), 2)+Math.pow((this.myYPos-b.myYPos),2));
	}
	public double calcForceExertedBy(Body p) {
		return 6.67*1e-11*(this.myMass*p.myMass)/Math.pow(calcDistance(p),2);
	}
	public double calcForceExertedByX(Body p) {
		return calcForceExertedBy(p)*(p.myXPos-this.myXPos)/calcDistance(p);
	}
	public double calcForceExertedByY(Body p) {
		return calcForceExertedBy(p)*(p.myYPos-this.myYPos)/calcDistance(p);
	}
	public double calcNetForceExertedByX(Body[] bodies) {
		double sumForces=0.0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				sumForces+=b.calcForceExertedByX(this);
			}
		}
		return -sumForces;
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double sumForces=0.0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				sumForces+=b.calcForceExertedByY(this);
			}
		}
		return -sumForces;
	}
	public void update(double deltaT, double xforce, double yforce) {
		double ax=xforce/this.myMass;
		double ay=yforce/this.myMass;
		double nvx=myXVel+deltaT*ax;
		double nvy=myYVel+deltaT*ay;
		double nx=myXPos+deltaT*nvx;
		double ny=myYPos+deltaT*nvy;
		myXPos=nx;
		myYPos=ny;
		myXVel=nvx;
		myYVel=nvy;
	}
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "image/"+myFileName);
	}
}
