package com.view.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.view.ui.frame.UIHome;

import javax.swing.JScrollPane;

public class DialogPaidBill extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// MARK:- Components
	private JRadioButton rbPrintBill;
	private JLabel lblDialog;
	private JPanel pnButton;
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel pnBill;
	private JLabel lblName;
	private JLabel lblAddress;
	private JLabel lblTel;
	private JLabel lblWaiter;
	private JLabel lblWaiterName;
	private JLabel lblForDate;
	private JLabel lblDate;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public DialogPaidBill(Object[][] orderList, JFrame frame) {
		setResizable(false);

		final JFrame finalFrame = frame;
		setTitle("Bill Confirm");
		setBounds(100, 100, 487, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		rbPrintBill = new JRadioButton("In H\u00F3a \u0110\u01A1n");
		rbPrintBill.setFont(new Font("Arial", Font.PLAIN, 12));
		rbPrintBill.setBounds(17, 417, 160, 15);
		contentPanel.add(rbPrintBill);

		addBill(orderList);

		lblDialog = new JLabel("X\u00E1c nh\u1EADn thanh to\u00E1n?");
		lblDialog.setBounds(10, 438, 250, 28);
		contentPanel.add(lblDialog);
		lblDialog.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDialog.setHorizontalAlignment(SwingConstants.LEFT);
		{
			pnButton = new JPanel();
			pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnButton, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				pnButton.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				pnButton.add(btnCancel);
			}
		}
	}

	public void addBtnOkListener(ActionListener listener) {
		this.btnOk.addActionListener(listener);
		;
	}

	public void addBtnCancelListener(ActionListener listener) {
		this.btnCancel.addActionListener(listener);
	}

	// Add bill to print
	private void addBill(Object[][] orderList) {
		pnBill = new JPanel();
		pnBill.setBackground(SystemColor.desktop);
		pnBill.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		pnBill.setBounds(10, 11, 451, 399);
		contentPanel.add(pnBill);
		pnBill.setLayout(null);

		lblName = new JLabel("Smart Restaurant");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(128, 11, 192, 26);
		pnBill.add(lblName);

		lblAddress = new JLabel("Hanoi University of Science");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(138, 30, 182, 26);
		pnBill.add(lblAddress);

		lblTel = new JLabel("Tel: 888-888-888");
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTel.setBounds(148, 49, 148, 26);
		pnBill.add(lblTel);

		lblDate = new JLabel("30/02/2019");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(345, 84, 80, 14);
		pnBill.add(lblDate);

		lblWaiter = new JLabel("Waiter:");
		lblWaiter.setBounds(23, 84, 58, 14);
		pnBill.add(lblWaiter);

		lblWaiterName = new JLabel("Peter Jackson");
		lblWaiterName.setBounds(78, 84, 84, 14);
		pnBill.add(lblWaiterName);

		lblForDate = new JLabel("Date:");
		lblForDate.setBounds(301, 86, 34, 11);
		pnBill.add(lblForDate);

		orderList = billList(orderList);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 124, 402, 249);
		pnBill.add(scrollPane);

		table = new JTable();
		table.setBackground(SystemColor.desktop);
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		table.setModel(
				new DefaultTableModel(orderList, new String[] { "Food", "Amount", "Price", "Discount", "Subtotal" }));

		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		table.getTableHeader().setReorderingAllowed(false);

		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		
		table.setCellSelectionEnabled(false);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

	}

	// Generate ordered food list for Bill printing
	private Object[][] billList(Object[][] defaultTable) {
		int numRow = 0;
		for (int i = 0; i < defaultTable.length; i++) {
			if (defaultTable[i][0] != null) {
				numRow++;
			}
		}
		Object[][] tmpList = new Object[numRow + 1][5];
		int j = 0;
		int sumAmount = 0;
		double sumPrice = 0;
		for (int i = 0; i < defaultTable.length; i++) {
			if (defaultTable[i][0] != null) {
				tmpList[j] = defaultTable[i];
				sumAmount += Integer.parseInt(defaultTable[i][1].toString());
				sumPrice += Double.parseDouble(defaultTable[i][4].toString());
				j++;
			}
		}
		tmpList[j][0] = "TOTAL";
		tmpList[j][1] = sumAmount;
		tmpList[j][4] = sumPrice;
		return tmpList;
	}

	public JRadioButton getRbPrintBill() {
		return rbPrintBill;
	}

	public void setRbPrintBill(JRadioButton rbPrintBill) {
		this.rbPrintBill = rbPrintBill;
	}

}
