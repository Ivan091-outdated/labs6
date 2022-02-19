package edu.spp;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;


public final class Application {

    private final JFrame mainFrame = new JFrame("Calculator");

    private final JPanel mainPanel = new JPanel();

    private final JButton pauseButton = new JButton("Pause");

    private final JButton resumeButton = new JButton("Resume");

    private final JTextArea textArea = new JTextArea(20, 20);

    private final JScrollPane areaScrollPane = new JScrollPane(textArea);

    private final StringBuffer stringBuffer = new StringBuffer();

    private final Semaphore semaphore = new Semaphore(0);

    private int k = 0;

    private double currentValue = 0;

    private final Thread thread = new Thread(() -> {
        try {
            while (true) {
                Thread.sleep(500);
                currentValue += 1 / Math.pow(2, k);
                stringBuffer.insert(0, String.valueOf(currentValue) + '\n');
                semaphore.acquire();
                textArea.setText(stringBuffer.toString());
                semaphore.release();
                k++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    });

    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 300);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumeButton.addMouseListener(new ResumeButtonListener());
        pauseButton.addMouseListener(new PauseButtonListener());
        pauseButton.setEnabled(false);
        mainFrame.add(mainPanel);
        mainPanel.add(resumeButton);
        mainPanel.add(pauseButton);
        mainPanel.add(areaScrollPane);
        mainFrame.setVisible(true);
        thread.start();
    }

    public static void main(String[] args) {
        new Application();
    }

    class PauseButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                if (semaphore.availablePermits() > 0){
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                    semaphore.acquire();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class ResumeButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (semaphore.availablePermits() == 0){
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(false);
                semaphore.release();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
