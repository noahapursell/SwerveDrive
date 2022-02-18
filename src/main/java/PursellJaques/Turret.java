/**
 * A class to manage the turret
 */
public class Turret {
    // Instance Variables
    public WPI_FalconFX topShooterMotor, bottomShooterMotor, turnShooterMotor;
    public final double TICKS_TO_DEGREES_CONSTANT = 1 * (1 / 2048) * (40 / 1) * ( 360 / 1) 
    // Convert from ticks to motor rotations (1 motor rotation per 2048 ticks) to turret rotations 
    // (1 turret rotation per 40 motor rotations) degrees (360 degrees per rotation)
    public final double DEGREES_TO_TICKS_CONSTANT = 1 / TICKS_TO_DEGREES_CONSTANT // Inverse of other number

    public Turret(int topShooterMotorCanID, int bottomSHooterMotorCanID, turnShooterMotorCanID){
        // Bottom Shooter Motor
        bottomShooterMotor = new WPI_TalonFX(bottomShooterMotorCanID);
        TalonFXConfiguration bottomShooterMotorConfig = new TalonFXConfiguration();
        bottomShooterMotorConfig.slot0.kP = Constants.SHOOTER_KP;
        bottomShooterMotorConfig.slot0.kI = Constants.SHOOTER_KI;
        bottomShooterMotorConfig.slot0.kD = Constants.SHOOTER_KD;
        bottomShooterMotor.configAllSettings(bottomShooterMotorConfig);
        bottomShooterMotor.selectProfileSlot(0, 0);
        bottomShooterMotor.setNeutralMode(NeutralMode.Brake);
        // Top Shooter Motor
        topShooterMotor = new WPI_TalonFX(topShooterMotorCanID);
        TalonFXConfiguration topShooterMotorConfig = new TalonFXConfiguration();
        topShooterMotorConfig.slot0.kP = Constants.SHOOTER_KP;
        topShooterMotorConfig.slot0.kI = Constants.SHOOTER_KI;
        topShooterMotorConfig.slot0.kD = Constants.SHOOTER_KD;
        topShooterMotor.configAllSettings(topShooterMotorConfig);
        topShooterMotor.selectProfileSlot(0, 0);
        topShooterMotor.setNeutralMode(NeutralMode.Brake);
        // turnShooterMotor
        turnShooterMotor = new WPI_TalonFX(turnShooterMotorCanID);
        TalonFXConfiguration turnShooterMotorConfig = new TalonFXConfiguration();
        turnShooterMotorConfig.slot0.kP = Constants.TURN_SHOOTER_MOTOR_KP;
        turnShooterMotorConfig.slot0.kI = Constants.TURN_SHOOTER_MOTOR_KI;
        turnShooterMotorConfig.slot0.kD = Constants.TURN_SHOOTER_MOTOR_KD;
        turnShooterMotor.configAllSettings(turnShooterMotorConfig);
        turnShooterMotor.selectProfileSlot(0, 0);
        turnShooterMotor.setNeutralMode(NeutralMode.Brake);
        // Set the shooter to have its current position as its zero point
        this.setAngleZeroPoint();
    }

    /**
     * Set the shooter's current position to be the position of 0 degrees
     * The code is set up so that 0 degrees needs to be directly forward
     */
    public void setAngleZeroPoint(){
        turnShooterMotor.setSelectedSensorPosition(0.0);
    }

    /**
     * Set the speeds of the shooting motors to specified value
     */
    public void setShooterSpeeds(double topMotorSpeed, double bottomMotorSpeed){
        topShooterMotor.set(ControlMode.Velocity, topMotorSpeed);
        bottomShooterMotor.set(ControlMode.Velocity, bottomMotorSpeed);
    }

    /**
     * Set the angle of the turret
     */
    public void setShooterAngle(double targetAngle){
        turnShooterMotor.set(COntrolMode.Positions, targetAngle * DEGREES_TO_TICKS_CONSTANT)

    }
}
