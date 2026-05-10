package first.robot;
import org.wpilib.hardware.expansionhub.ExpansionHubMotor;
import org.wpilib.math.controller.PIDController;
public class controlledMotor {
    private PIDController pidController = null;
    private ExpansionHubMotor motor = null;

    public controlledMotor(int hub, int port, double p, double i, double d){
        pidController = new PIDController(p, i, d);
        motor = new ExpansionHubMotor(hub, port);

    }

    public void setTargetPosition(double position){
        pidController.setSetpoint(position);
    }
    public void update(){
        double currentPosition = motor.getEncoderPosition();
        double power = pidController.calculate(currentPosition);
        motor.setThrottle(power);
    }
    
}
