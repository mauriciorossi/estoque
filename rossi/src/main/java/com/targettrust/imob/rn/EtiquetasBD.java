package com.targettrust.imob.rn;

import com.targettrust.imob.bean.ProdutoBean;
import com.targettrust.imob.conexao.ConexaoDB;
import com.targettrust.imob.util.Mensagem;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

public class EtiquetasBD extends HttpServlet {

    public boolean excluiEtiquetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection con = ConexaoDB.getConnection();
        try {
            StringBuffer sql = new StringBuffer("DELETE FROM etiquetas ");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean salvaEtiquetas(ProdutoBean produto) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection con = ConexaoDB.getConnection();
        try {
            int quantidade = produto.getQuantidadeEtiquetas();
            System.out.println("teste" + quantidade);
            int j = 0;
            StringBuffer sql = new StringBuffer("SELECT count(*) as total FROM etiquetas");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            int qtdTabela = 0;
            if (rs.next()) {
                qtdTabela = rs.getInt("total");
            }

            if (qtdTabela == 60 || qtdTabela + quantidade > 60) {
                int disponivel = 60;
                disponivel = disponivel - qtdTabela;
                Mensagem.add("Só é possivel adicionar: " + disponivel + " Etiquetas");
                return false;
            } else {
                while (j < quantidade) {
                    sql = new StringBuffer("INSERT INTO etiquetas (prodcodigo, prodTamanho, prodReferencia, prodPreco) values(?,?,?,?)");
                    pstmt = con.prepareStatement(sql.toString());
                    int i = 1;
                    pstmt.setInt(i++, produto.getProdCodigo());
                    pstmt.setString(i++, produto.getProdTamanho());
                    pstmt.setString(i++, produto.getProdReferencia());
                    pstmt.setDouble(i++, produto.getProdValorUnitario());
                    pstmt.executeUpdate();
                    j++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String geraEtiquetas() throws Exception {
        Connection conn = ConexaoDB.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("SELECT parCaminhoPdf FROM caminhos");
            ResultSet rs = pstmt.executeQuery();
            String parCaminhoPDF = "";
            if (rs.next()) {
                parCaminhoPDF = rs.getString("parCaminhoPdf");
            }

            StringBuffer sql = new StringBuffer("select prodCodigo, prodTamanho, prodReferencia, prodPreco from etiquetas ");
            pstmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
                JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
                //--PARÂMETROS QUE SERÃO ENVIADOS AO RELATÓRIO--//
                Map<String, Object> parametros = new HashMap();
                parametros.put("paramTitulo2", new String("Impressão de Ficha Cadastral: "));
                //parametros.put("paramImagem", new String(util.pegaLogoEmpresa(empcodigo)));
                //parametros.put("paramHora", "Hora de Emissão : ");
                //parametros.put("paramSistema", new String(util.pegaNomeVersaoSistema()));
                
                File arquivo = new File("texto.txt");
                System.out.println("teste1" + arquivo.getAbsoluteFile());
                System.out.println("teste2" + arquivo.getAbsolutePath());
                String teste = FacesContext.getCurrentInstance().getExternalContext().getRealPath("EtiquetasBD.java");
                System.out.println("teste3" + teste);
                String localPath = System.getProperty("user.dir");
                System.out.println("teste4" + localPath);
                File dir1 = new File(".");
                File dir2 = new File("..");
                System.out.println("Diretório Atual: " + dir1.getCanonicalPath());
                System.out.println("Diretório Pai: " + dir2.getCanonicalPath());
                
                JasperPrint impressao = null;
                //String caminhoRelJasper = (parCaminhoPDF);
                //--PASSA A CONEXÃO PARA O RELATÓRIO MASTER--//
                impressao = JasperFillManager.fillReport(parCaminhoPDF + "reportEtiquetas.jasper", parametros, conn);

                //--PASSA O RESULTSET PARA O RELATÓRIO MASTER--//
                impressao = JasperFillManager.fillReport(parCaminhoPDF + "reportEtiquetas.jasper", parametros, jrRS);

                //JasperViewer.viewReport(impressao);
                //--MONTA O NOME DO ARQUIVO PDF--//
                 String nomeArquivoPDF = new Double(123456).toString() + ".pdf";
                //--EXPORTA ARQUIVO JASPER PARA ARQUIVO PDF--//
                JasperExportManager.exportReportToPdfFile(impressao, parCaminhoPDF + nomeArquivoPDF);
                File file = new File(parCaminhoPDF + nomeArquivoPDF);
                if (file.exists()) {
                    System.out.println("o pdf existe meu amigo!");
                    downloadFile("123456.0.pdf", parCaminhoPDF, "txt", FacesContext.getCurrentInstance());
                }
                //   Desktop.getDesktop().open(new File(parCaminhoPDF + nomeArquivoPDF));
                //return "../imob/reports/" + nomeArquivoPDF;
                return "../imob/reports/";
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            return "";
        } catch (JRException e) {
            System.out.println(e);
            System.err.print(e.getMessage());
            return "";
        } finally {
            //Util.closeResource(pstmt);
            //Sdb.freeConnection(con, "ImprimeFichasCadastrais");
        }
    }
    public static synchronized void downloadFile(String filename, String fileLocation, String mimeType,
            FacesContext facesContext) {

        ExternalContext context = facesContext.getExternalContext(); // Context  
        String path = fileLocation; // Localizacao do arquivo  
        String fullFileName = path + filename;
        File file = new File(fullFileName); // Objeto arquivo mesmo :)  

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\""); //aki eu seto o header e o nome q vai aparecer na hr do donwload  
        response.setContentLength((int) file.length()); // O tamanho do arquivo  
        response.setContentType(mimeType); // e obviamente o tipo  

        try {
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            byte[] buf = new byte[(int) file.length()];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            in.close();
            out.flush();
            out.close();
            facesContext.responseComplete();
        } catch (IOException ex) {
            System.out.println("Error in downloadFile: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
