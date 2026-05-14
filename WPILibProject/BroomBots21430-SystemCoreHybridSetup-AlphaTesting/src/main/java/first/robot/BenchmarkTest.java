package first.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.wpilib.hardware.expansionhub.ExpansionHubMotor;
import org.wpilib.opmode.PeriodicOpMode;
import org.wpilib.opmode.Teleop;
import org.wpilib.util.Preferences;

    
@Teleop
public class BenchmarkTest extends PeriodicOpMode{
    public class Logger{
        String name = "SystemCore21430";
        public static HashMap<String, Object> data = new HashMap<String,Object>();     
        void putData(String key, Object value){
            data.put(key, value);
        }
        Object get(String key){
            return data.get(key);
        }
        public Logger(){
            putData("name", name);
            putData("data_store",0);
            putData("i2cFast",0);
        }
        void show(){
            System.out.print(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",get("name"),get("start"),get("switch_motor"),get("set"),get("doubles"),get("doubles2"),get("data_store"),get("blackboard"),get("i2cFast"),get("i2c")));
            System.out.println();
        }
    }
    public Logger logger = this.new Logger();
    public class FunctionTimer{
        FunctionTimer(String _name){
            name = _name;
            startTime = now();
        }
        long now(){return System.currentTimeMillis();}
        String name;
        long startTime;
        long get(){return now() - startTime;}
        void log(){
            Object value = get();
            System.out.print(name+value);
            System.out.println();
            logger.putData(name, value);
        }
    }
    FunctionTimer startTimer = new FunctionTimer("start");


    public ExpansionHubMotor motor0;
    // public I2C colorSensor;

    @Override
    public void start(){
        motor0 = new ExpansionHubMotor(0, 0);// feel free to change this
        // colorSensor = new I2C(I2C.Port.PORT_0, 0);
        startTimer.log();
    }

    @Override
    public void periodic(){
        FunctionTimer motorState = new FunctionTimer("switch_motor");
        motor0.resetEncoder();
        motorState.log();
        FunctionTimer setPower = new FunctionTimer("set");
        motor0.setThrottle(1);
        motor0.setThrottle(0);
        setPower.log();
        FunctionTimer doubleTimer = new FunctionTimer("doubles");
        int lot = 1000000;
        double[] doubles = new double[lot];
        for(int i = 0; i < lot; i++){
            doubles[i] = Math.random();
        }
        doubleTimer.log();
        FunctionTimer double2Timer = new FunctionTimer("doubles2");
        List<Double> mutableList = new ArrayList<>();
        for(int i = 0; i < lot; i++){
            mutableList.add(Math.random());
        }
        double2Timer.log();
        FunctionTimer blackboardTimer = new FunctionTimer("blackboard");
        Preferences.setDouble("kP", 0.2); 
        double kP = Preferences.getDouble("kP", 0.1); 
        blackboardTimer.log();
        FunctionTimer i2cTimer = new FunctionTimer("i2c");
        // unfortunate limited support
        i2cTimer.log();
        close();// or end()
    }
}