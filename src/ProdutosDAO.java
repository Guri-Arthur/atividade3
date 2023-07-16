
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
     
    try {
    PreparedStatement stmt = this.conn.prepareStatement(sql);
    stmt.setString(1, produto.getNome());
    stmt.setDouble(2, produto.getValor());                      
    stmt.setString(3, produto.getStatus());
    stmt.execute();
    System.out.println("cadastro realizado com sucesso.");
      } catch (Exception e) {
      System.out.println("Erro ao cadastrar: " + e.getMessage());
      }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
                String sql = "SELECT * FROM produtos";
                
           try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();            
                    
            ArrayList<ProdutosDTO> listProdutos = new ArrayList<>();
                while (rs.next()) { 
                    ProdutosDTO p = new ProdutosDTO();
                        
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setStatus(rs.getString("status"));
                    p.setValor(rs.getInt("valor"));
                        
                    listProdutos.add(p);    
                }
             return listProdutos;
                
            } catch (Exception e) {
                return null;
        }
    }
    
             public void venderProduto(int id){
                String sql = "UPDATE produtos SET status=Vendido WHERE id=?";
                try {                 
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ProdutosDTO p = new ProdutosDTO();
                    stmt.setString(1,p.getStatus());
                    
                    stmt.execute();
                    
                } catch (Exception e) {
                    System.out.println("Erro ao editar empresa: " + e.getMessage());
                }
            }
    
        
}

