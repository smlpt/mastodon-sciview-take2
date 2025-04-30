# manvr3d - V0.2 Quick Start Guide

This packaged version of manvr3d is only for testing purposes and not ready yet for day to day use. A list of known issues is listed below.



### Getting started

Simply execute the `mastodon-sciview-bridge` batch file in the `bin` folder. At this time, we only support the Windows platform due to support of VR hardware.
After launch, a Mastodon and a Fiji window will open. From the Mastodon window, you can open existing projects or create new ones from your image dataset.

With a project open, you can launch a new manvr3d instance by going to `Window > New manvr3d window`. This will open a sciview window, the GUI associated with it, a BigDataViewer (BDV) window that is linked to sciview and also an ELEPHANT control panel.

To configure ELEPHANT, please follow the official [ELEPHANT Docs](https://elephant-track.github.io/). It needs to be set up with docker either locally or running on a remote server (recommended). You will also need to give individual dataset names for each dataset in the ELEPHANT preferences panel found in `Plugins > ELEPHANT > Preferences`. There, you can also configure the remote server connection you want to use. Also make sure to click both of the lock icons for group 1 found in the top left of the linked BDV window and the manvr3d GUI.

VR is currently only tested to work on Windows systems. To launch a VR session, you need a SteamVR-compatible headset connected to your computer, and Steam needs to be opened. Eye tracking support is highly experimental and currently relies on hardware from Pupil Labs and running the Pupil service on your system. We recommend trying out VR on a mainstream headset like the Meta Quest 2 for now and disabling the option `Launch with Eye Tracking` in the GUI.

### Known issues

- manvr3d currently only works with 16bit images [↗](https://github.com/scenerygraphics/manvr3d/issues/19).

- The following volume processing input fields are currently not functional: contrast changing factor, shifting bias, gamma level and clamping. These fields will be reactivated once we support images with different bit depths [↗](https://github.com/scenerygraphics/manvr3d/issues/20).

- The close button in the manvr3d GUI is currently not functional. Close the Fiji window instead [↗](https://github.com/scenerygraphics/manvr3d/issues/21).

- Scene clean-up after closing a VR session is not complete. Camera behavior in the sciview window [switches to first person](https://github.com/scenerygraphics/sciview/issues/612) input instead of arcball control. This will be addressed in a future update.

- Spot scaling in sciview can be inconsistent with the spots rendered in BDV. This applies also to spots added by ELEPHANT (they tend to be too small) [↗](https://github.com/scenerygraphics/manvr3d/issues/22).

- A freshly opened sciview window can have black borders in the bottom right area. This is caused by Windows scaling set to something other than 100%. Resizing the sciview window fixes it. [This issue](https://github.com/scenerygraphics/sciview/issues/553) will be addressed in a future update.


