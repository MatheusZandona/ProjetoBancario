package br.univel.relatorios;

import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ExecRelatorio {
	
	public boolean executarRelatorio(String nomeArquivo, JRDataSource datasource){					
		boolean resultado = false;
		
		JasperPrint jp = null;
		nomeArquivo  = this.getClass()
					   .getClassLoader()
					   .getResource("")
					   .getPath()
					   .concat("/br/univel/relatorios/")
					   .concat(nomeArquivo);  
		
		try {
			jp = JasperFillManager.fillReport(nomeArquivo, null, datasource);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		
		JasperViewer jasperViewer = new JasperViewer(jp, false);
		jasperViewer.setBounds(50, 50, 320, 240);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jasperViewer.setVisible(true);
		resultado = true;
		
		return resultado;
	}		

}
