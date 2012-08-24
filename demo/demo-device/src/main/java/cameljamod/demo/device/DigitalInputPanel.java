/*
 *  Copyright 2012 Steven Swor.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cameljamod.demo.device;

import net.wimpi.modbus.procimg.DigitalIn;

/**
 *
 * @author Steven Swor
 */
public class DigitalInputPanel extends javax.swing.JPanel implements DigitalIn{

    private int referenceAddress;

    /**
     * Creates new form DigitalInputPanel
     */
    public DigitalInputPanel(final int referenceAddress, final boolean active) {
        initComponents();
        setReferenceAddress(referenceAddress);
        statusCheckBox.setSelected(active);
    }

    public final int getReferenceAddress() {
        return referenceAddress;
    }

    public final void setReferenceAddress(final int referenceAddress) {
        this.referenceAddress = referenceAddress;
        statusCheckBox.setText(String.valueOf(referenceAddress));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusCheckBox = new javax.swing.JCheckBox();

        statusCheckBox.setText("referenceAddress");
        statusCheckBox.setEnabled(false);
        add(statusCheckBox);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox statusCheckBox;
    // End of variables declaration//GEN-END:variables

    public boolean isSet() {
        return statusCheckBox.isSelected();
    }
}
