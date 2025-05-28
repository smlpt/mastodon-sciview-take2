package org.mastodon.mamut

import graphics.scenery.controls.OpenVRHMD
import graphics.scenery.controls.TrackerRole
import graphics.scenery.controls.OpenVRHMD.Manufacturer
import graphics.scenery.controls.OpenVRHMD.OpenVRButton


/** This input mapping manager provides several preconfigured profiles for different VR controller layouts. */
object ButtonMappingManager {

    lateinit var eyeTracking: ButtonConfig
    lateinit var controllerTracking: ButtonConfig
    lateinit var moveObserver: ButtonConfig
    lateinit var grabObserver: ButtonConfig
    lateinit var grabSpot: ButtonConfig
    lateinit var playback: ButtonConfig
    lateinit var cycleMenu: ButtonConfig
    lateinit var faster: ButtonConfig
    lateinit var slower: ButtonConfig
    lateinit var stepFwd: ButtonConfig
    lateinit var stepBwd: ButtonConfig
    lateinit var addDeleteReset: ButtonConfig
    lateinit var select: ButtonConfig

    private var currentProfile: Manufacturer = Manufacturer.Oculus

    init {
        loadProfile()
    }

    private val profiles = mapOf(
        Manufacturer.HTC to mapOf(
            "eyeTracking" to ButtonConfig(),
            "controllerTracking" to ButtonConfig(),
            "moveObserver" to ButtonConfig(),
            "grabObserver" to ButtonConfig(),
            "grabSpot" to ButtonConfig(),
            "playback" to ButtonConfig(),
            "cycleMenu" to ButtonConfig(),
            "faster" to ButtonConfig(),
            "slower" to ButtonConfig(),
            "stepFwd" to ButtonConfig(),
            "stepBwd" to ButtonConfig(),
            "addDeleteReset" to ButtonConfig(),
            "select" to ButtonConfig()
        ),

        Manufacturer.Oculus to mapOf(
            "eyeTracking" to ButtonConfig(TrackerRole.LeftHand, OpenVRButton.Trigger),
            "controllerTracking" to ButtonConfig(TrackerRole.RightHand, OpenVRButton.Trigger),
            "moveObserver" to ButtonConfig(TrackerRole.LeftHand, OpenVRButton.Side),
            "grabObserver" to ButtonConfig(),
            "grabSpot" to ButtonConfig(),
            "playback" to ButtonConfig(),
            "cycleMenu" to ButtonConfig(),
            "faster" to ButtonConfig(),
            "slower" to ButtonConfig(),
            "stepFwd" to ButtonConfig(),
            "stepBwd" to ButtonConfig(),
            "addDeleteReset" to ButtonConfig(),
            "select" to ButtonConfig()
        )
    )

    fun switchProfile(profile: Manufacturer) {
        currentProfile = profile
        loadProfile()
    }

    /** Load the current profile's button mapping */
    fun loadProfile() {
        val profile = profiles[currentProfile] ?: return
        eyeTracking = profile["eyeTracking"]?.copy() ?: eyeTracking
        controllerTracking = profile["controllerTracking"]?.copy() ?: controllerTracking
        moveObserver = profile["moveObserver"]?.copy() ?: moveObserver
        grabObserver = profile["grabObserver"]?.copy() ?: grabObserver
        grabSpot = profile["grabSpot"]?.copy() ?: grabSpot
        playback = profile["playback"]?.copy() ?: playback
        cycleMenu = profile["cycleMenu"]?.copy() ?: cycleMenu
        faster = profile["faster"]?.copy() ?: faster
        slower = profile["slower"]?.copy() ?: slower
        stepFwd = profile["stepFwd"]?.copy() ?: stepFwd
        stepBwd = profile["stepBwd"]?.copy() ?: stepBwd
        addDeleteReset = profile["addDeleteReset"]?.copy() ?: addDeleteReset
        select = profile["select"]?.copy() ?: select

    }

    fun getCurrentMapping(): Map<String, ButtonConfig> = mapOf(
        "eyeTracking" to eyeTracking,
        "controllerTracking" to controllerTracking,
        "moveObserver" to moveObserver,
        "grabObserver" to grabObserver,
        "grabSpot" to grabSpot,
        "playback" to playback,
        "cycleMenu" to cycleMenu,
        "faster" to faster,
        "slower" to slower,
        "stepFwd" to stepFwd,
        "stepBwd" to stepBwd,
        "addDeleteReset" to addDeleteReset,
        "select" to select
    )
}


/** Combines the [TrackerRole] ([r]) and the [OpenVRHMD.OpenVRButton] ([b]) into a single configuration. */
data class ButtonConfig (
    var r: TrackerRole,
    var b: OpenVRHMD.OpenVRButton
)