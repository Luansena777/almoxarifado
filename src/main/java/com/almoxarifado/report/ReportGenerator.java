package com.almoxarifado.report;

import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    public static void generateReport() {
        try {
            // Caminho do arquivo .jrxml
            String caminho = "src/main/resources/reports/stock_report.jrxml";

            // Compilar o relatório
            JasperReport jasperReport = JasperCompileManager.compileReport(caminho);

            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Titulo", "Relatório de Movimentações de Produtos");

            // Conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3306/almoxarifado";
            String user = "root";
            String senha = "12345678";
            Connection connection = DriverManager.getConnection(url, user, senha);

            // Preencher o relatório com os dados
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);

            // Exportar o relatório para PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/movimentacoes.pdf");

            System.out.println("Relatório gerado com sucesso!");
        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
