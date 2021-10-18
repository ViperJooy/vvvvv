package com.viper.vvvvv.data

/**
 * author: viper
 * date: 2021-08-19 4:35 下午
 * desc: StateModel
 */

data class StateModel(
    val additionalVehicleStatus: AdditionalVehicleStatus,
    val basicVehicleStatus: BasicVehicleStatus,
    val configuration: Configuration,
    val notification: Notification,
    val parkTime: ParkTime,
    val temStatus: TemStatus,
    val theftNotification: TheftNotification,
    val updateTime: Long
)

data class AdditionalVehicleStatus(
    val climateStatus: ClimateStatus,
    val drivingSafetyStatus: DrivingSafetyStatus,
    val electricVehicleStatus: ElectricVehicleStatus,
    val maintenanceStatus: MaintenanceStatus,
    val pollutionStatus: PollutionStatus,
    val runningStatus: RunningStatus
)

data class BasicVehicleStatus(
    val direction: String,
    val distanceToEmpty: String,
    val engineStatus: String,
    val position: Position,
    val speed: String,
    val speedValidity: String,
    val usageMode: String
)

data class Configuration(
    val vin: String
)

data class Notification(
    val reason: String,
    val time: String
)

data class ParkTime(
    val status: String
)

data class TemStatus(
    val networkAccessStatus: NetworkAccessStatus
)

data class TheftNotification(
    val activated: String,
    val time: String
)

data class ClimateStatus(
    val exteriorTemp: String,
    val exteriorTempValidity: String,
    val preClimateActive: Boolean,
    val sunroofOpenStatus: String,
    val winStatusDriver: String,
    val winStatusDriverRear: String,
    val winStatusPassenger: String,
    val winStatusPassengerRear: String
)

data class DrivingSafetyStatus(
    val centralLockingStatus: String,
    val doorLockStatusDriver: String,
    val doorLockStatusDriverRear: String,
    val doorLockStatusPassenger: String,
    val doorLockStatusPassengerRear: String,
    val doorOpenStatusDriver: String,
    val doorOpenStatusDriverRear: String,
    val doorOpenStatusPassenger: String,
    val doorOpenStatusPassengerRear: String,
    val engineHoodOpenStatus: String,
    val seatBeltStatusDriver: String,
    val seatBeltStatusDriverRear: String,
    val seatBeltStatusPassenger: String,
    val seatBeltStatusPassengerRear: String,
    val trunkLockStatus: String,
    val trunkOpenStatus: String
)

data class ElectricVehicleStatus(
    val chargeLevel: String,
    val chargerState: String,
    val distanceToEmptyOnBatteryOnly: String,
    val stateOfCharge: String,
    val timeToFullyCharged: String
)

data class MaintenanceStatus(
    val brakeFluidLevelStatus: String,
    val daysToService: String,
    val distanceToService: String,
    val engineHrsToService: String,
    val mainBatteryStatus: MainBatteryStatus,
    val odometer: String,
    val serviceWarningStatus: String,
    val tyrePreWarningDriver: String,
    val tyrePreWarningDriverRear: String,
    val tyrePreWarningPassenger: String,
    val tyrePreWarningPassengerRear: String,
    val washerFluidLevelStatus: String
)

data class PollutionStatus(
    val exteriorPM25: String,
    val interiorPM25: String
)

data class RunningStatus(
    val aveFuelConsumption: String,
    val aveFuelConsumptionInLatestDrivingCycle: String,
    val avgSpeed: String,
    val engineCoolantLevelStatus: String,
    val engineCoolantTemperature: String,
    val engineCoolantTemperatureValidity: String,
    val engineOilLevelStatus: String,
    val engineOilPressureWarning: String,
    val fuelLevel: String,
    val fuelLevelStatus: Int,
    val tripMeter1: String,
    val tripMeter2: String
)

data class MainBatteryStatus(
    val stateOfCharge: String,
    val voltage: String
)

data class Position(
    val altitude: String,
    val latitude: String,
    val longitude: String,
    val marsCoordinates: String,
    val posCanBeTrusted: String
)

data class NetworkAccessStatus(
    val simInfo: List<Any>
)