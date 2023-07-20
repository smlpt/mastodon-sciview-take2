//TODO add license text
package org.mastodon.mamut;

import javax.swing.*;
import java.awt.*;

public class SciviewBridgeUI {
	SciviewBridge controlledBridge;
	final Container contentPane;

	public SciviewBridgeUI(final SciviewBridge controlledBridge, final Container populateThisContainer) {
		this.controlledBridge = controlledBridge;
		this.contentPane = populateThisContainer;
		populatePane();
		updatePaneValues();
	}

	public Container getControlsWindowPanel() {
		return contentPane;
	}

	public SciviewBridge getControlledBridge() {
		return controlledBridge;
	}

	// -------------------------------------------------------------------------------------------
	void populatePane() {
		final GridBagLayout gridBagLayout = new GridBagLayout();
		contentPane.setLayout( gridBagLayout );

		final GridBagConstraints c = new GridBagConstraints();
		final Insets defSpace = new Insets(2,10,2,10);
		c.insets = defSpace;
		c.anchor = GridBagConstraints.LINE_START;

		c.gridy = 0;
		insertNote("Volume pixel values 'v' are processed:   min( exp( contrast*v, gamma ), not_above )", c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Apply on Volume this contrast factor:", c);
		//
		c.gridx = 1;
		INTENSITY_CONTRAST = new SpinnerNumberModel(1.0, 0.0, 100.0, 0.5);
		insertSpinner(INTENSITY_CONTRAST, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Apply on Volume this gamma level:", c);
		//
		c.gridx = 1;
		INTENSITY_GAMMA = new SpinnerNumberModel(1.0, 0.1, 3.0, 0.1);
		insertSpinner(INTENSITY_GAMMA, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Set to zero any voxel whose value would have been above:", c);
		//
		c.gridx = 1;
		INTENSITY_CLAMP_AT_TOP = new SpinnerNumberModel(700.0, 0.0, 65535.0, 50.0);
		insertSpinner(INTENSITY_CLAMP_AT_TOP, c);

		// -------------- separator --------------
		c.gridy++;
		insertSeparator(c);

		c.gridy++;
		insertNote("Shortcuts to the standard sciview view intensity scaling", c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Volume intensity bottom clamp:", c);
		//
		c.gridx = 1;
		INTENSITY_RANGE_MIN = new SpinnerNumberModel(0.0, 0.0, 65535.0, 50.0);
		insertSpinner(INTENSITY_RANGE_MIN, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Volume intensity upper clamp:", c);
		//
		c.gridx = 1;
		INTENSITY_RANGE_MAX = new SpinnerNumberModel(2500.0, 0.0, 65535.0, 50.0);
		insertSpinner(INTENSITY_RANGE_MAX, c);

		// -------------- separator --------------
		c.gridy++;
		insertSeparator(c);

		c.gridy++;
		insertNote("Parameters of the spots in-painting into the Volume", c);

		c.gridy++;
		INTENSITY_OF_COLORS_APPLY = new JCheckBox("Enable spots in-painting into the Volume (visible only on next repainting of Volume)");
		insertCheckBox(INTENSITY_OF_COLORS_APPLY, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("Next repainting of Volume will be triggered:", c);
		//
		c.gridx = 1;
		UPDATE_VOLUME_AUTOMATICALLY = new JComboBox<>(new String[] {updVolMsgA, updVolMsgM});
		insertRColumnItem(UPDATE_VOLUME_AUTOMATICALLY, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("When repainting, draw colors at this nominal intensity:", c);
		//
		c.gridx = 1;
		INTENSITY_OF_COLORS = new SpinnerNumberModel(2400.0, 0.0, 65535.0, 50.0);
		insertSpinner(INTENSITY_OF_COLORS, c);

		c.gridy++;
		c.gridx = 0;
		insertLabel("When repainting, multiply spots radii with:", c);
		//
		c.gridx = 1;
		SPOT_RADIUS_SCALE = new SpinnerNumberModel(3.0, 0.0, 50.0, 1.0);
		insertSpinner(SPOT_RADIUS_SCALE, c);

		c.gridy++;
		UPDATE_VOLUME_VERBOSE_REPORTS = new JCheckBox("Verbose/debug reporting during Volume repainting");
		insertCheckBox(UPDATE_VOLUME_VERBOSE_REPORTS, c);

		// -------------- button row --------------
		c.gridy++;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener( (e) -> controlledBridge.detachControllingUI() );
		insertRColumnItem(closeBtn, c);
	}

	final Insets noteSpace = new Insets(2,10,8,20);
	void insertNote(final String noteText, final GridBagConstraints c) {
		final int prevGridW = c.gridwidth;
		final Insets prevInsets = c.insets;

		c.gridwidth = 2;
		c.insets = noteSpace;
		c.weightx = 0.1;

		c.gridx = 0;
		contentPane.add( new JLabel(noteText), c);

		c.gridwidth = prevGridW;
		c.insets = prevInsets;
	}

	void insertLabel(final String labelText, final GridBagConstraints c) {
		c.weightx = 0.5;
		contentPane.add(new JLabel(labelText), c);
	}

	final Dimension spinnerMinDim = new Dimension(200,20);
	void insertSpinner(final SpinnerModel model, final GridBagConstraints c) {
		insertRColumnItem(new JSpinner(model), c);
	}
	void insertRColumnItem(final JComponent item, final GridBagConstraints c) {
		item.setMinimumSize(spinnerMinDim);
		item.setPreferredSize(spinnerMinDim);
		c.weightx = 0.3;
		contentPane.add(item, c);
	}

	void insertCheckBox(final JCheckBox cbox, final GridBagConstraints c) {
		final int prevFill = c.fill;
		final int prevGridW = c.gridwidth;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;

		c.gridx = 0;
		c.weightx = 0.1;
		contentPane.add(cbox, c);

		c.fill = prevFill;
		c.gridwidth = prevGridW;
	}

	final Insets sepSpace = new Insets(8,10,8,10);
	void insertSeparator(final GridBagConstraints c) {
		final int prevFill = c.fill;
		final int prevGridW = c.gridwidth;
		final Insets prevInsets = c.insets;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 0.1;
		c.insets = sepSpace;

		c.gridx = 0;
		contentPane.add( new JSeparator(JSeparator.HORIZONTAL), c);

		c.fill = prevFill;
		c.gridwidth = prevGridW;
		c.insets = prevInsets;
	}

	/**
	 * Disable all listeners to make sure that, even if this UI window would ever
	 * be re-displayed, its controls could not control anything (and would throw
	 * NPEs if the controls would actually be used).
	 */
	public void deactivateAndForget() {
		//listeners tear-down here
		this.controlledBridge = null;
	}

	public void updatePaneValues() {
		UPDATE_VOLUME_AUTOMATICALLY.setEnabled( controlledBridge.UPDATE_VOLUME_AUTOMATICALLY );
	}

	//int SOURCE_ID = 0;
	//int SOURCE_USED_RES_LEVEL = 0;
	SpinnerModel INTENSITY_CONTRAST;
	SpinnerModel INTENSITY_CLAMP_AT_TOP;
	SpinnerModel INTENSITY_GAMMA;

	SpinnerModel INTENSITY_OF_COLORS;

	SpinnerModel INTENSITY_RANGE_MAX;
	SpinnerModel INTENSITY_RANGE_MIN;

	JCheckBox INTENSITY_OF_COLORS_APPLY;
	SpinnerModel SPOT_RADIUS_SCALE;

	static final String updVolMsgA = "Automatically";
	static final String updVolMsgM = "Manually";
	private JComboBox<String> UPDATE_VOLUME_AUTOMATICALLY;
	private JCheckBox UPDATE_VOLUME_VERBOSE_REPORTS;
}