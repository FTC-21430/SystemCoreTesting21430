# Test Info

This test benchmark is an attempt to find some correlation between the SystemCore and Rev Control Hub, mostly to gauge the enhancements to the SystemCore device.

## Devices

Motor

Rev Color Sensor V3

## Code

**Scribe** is a wrapper for the default Android logger, with a couple enhancements. Used in this case to create the .csv test files.

### Control Hub

```java
@TeleOp
public class BenchMarker extends LinearOpMode {
    Timer startTimer = new Timer("start");


    class Timer {
        String name;
        public Timer(String name){
            this.name = name;
        }
        double start = System.currentTimeMillis();
        double get(){
            return System.currentTimeMillis() - start;
        }
        void log(){
            Scribe.getInstance().logData(name + this.get() + "ms");
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        RevColorSensorV3 c1 = hardwareMap.get(RevColorSensorV3.class, "testi2c");
        RevColorSensorV3 c2 = hardwareMap.get(RevColorSensorV3.class, "testi2cFAST");

        startTimer.log();
        waitForStart();
        Timer motorState = new Timer("Switch Motor State to Reset -> Use Encoder");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorState.log();
        Timer motorPower = new Timer ("Set 1-> 0");
        motor.setPower (1);
        motor.setPower (0);
        motorPower.log();
        Timer listDoubles = new Timer ("A lot of doubles (arr)");
        int lot = 1000000;
        double[] doubles = new double[lot];
        for(int i = 0; i < lot; i++){
            doubles[i] = Math.random();
        }
        listDoubles.log();
        Timer list2Doubles = new Timer("A lot of doubles (mutableList)");
        List<Double> mutableList = new ArrayList<>();
        for(int i = 0; i < lot; i++){
            mutableList.add(Math.random());
        }
        list2Doubles.log();
        Timer dataStore = new Timer ("Data storage");
        DataStorage.setAlliance(Alliance.BLUE);
        DataStorage.getAlliance();
        dataStore.log();
        Timer blacksboard = new Timer("Blackboard");
        blackboard.put("allinace",0);
        blackboard.get("allinace");
        blacksboard.log();
        Timer i2c = new Timer("I2CFast");
        NormalizedRGBA colors = c2.getNormalizedColors();
        double distance = c2.getDistance(DistanceUnit.INCH);
        c2.enableLed(true);
        i2c.log();
        Timer i2cslow = new Timer("I2C");
        NormalizedRGBA colors2 = c1.getNormalizedColors();
        double distance2 = c1.getDistance(DistanceUnit.INCH);
        c1.enableLed(true);
        i2cslow.log();

    }
}
```

### System Core

Located inside of `SystemCoreTesting21430\WPILibProject\BroomBots21430-SystemCoreHybridSetup-AlphaTesting\src\main\java\first\robot\`

## CSV Header

`name,start,switch_motor,set,doubles,doubles2,data_store,blackboard,i2cFast,i2c`

## Tests

* name

Consists of the name of the device. This was mostly used for CHUB averages testing

* start

The time it takes the system to boot, as well as gathering all of the hardware map devices (motor, colorsensor1, colorsensor2)

* switch_motor

Switches motor to state `STOP_AND_RESET_ENCODER` to `RUN_WITHOUT_ENCODER`

* set

Sets motor power to `1` and back to `0`

* doubles

Creates a `double[]` list of `1000000` doubles with `Math.random()` to test CPU/RAM usage

* doubles2

Same thing as doubles, just uses a `List<Double>` to test another scenario

* data_store

A custom data storage platform based on saving and retrieving files at runtime. All SC tests will be `0` for this test

* blackboard

FTC Hashmap implementation of above data_store. Used to sync data between `AUTO` and `TELEOP`. **SystemCore** implementation...

* i2cFast

Based on popular rumors, I tested the theory that i2c is faster in ports `1,3` on CHUB. For SC test, this will be `0` always

* i2c

Comparison to i2cFast. This will be used on SystemCore with a color sensor, using both getting colors and distance, and enabling the LED.

## Results

Focusing on Control Hub, i2cFast is actually slower than i2c test.
