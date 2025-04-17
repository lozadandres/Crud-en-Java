package crudarchivos;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClienteServicios {
    private final String RUTA_CSV="Clientes.csv";
    private final String SEPARADOR=";";
    
    public ClienteServicios(){}
    
    public void crearCliente(Cliente cliente){
        try(FileWriter fw=new FileWriter(RUTA_CSV,true);
            BufferedWriter bw =new BufferedWriter(fw)){
            String renglon=cliente.getCedula()+SEPARADOR
                           +cliente.getNombre()+SEPARADOR
                           +cliente.getTelefono();
            bw.write(renglon);
            bw.newLine();
            System.out.println("Cliente guardado con exito");
        }catch (IOException ex) {
            System.out.println("Error al guardar el cliente");      
        } 
    }
    public ArrayList<Cliente> leerClientes(){
        ArrayList<Cliente> listaClientes=new ArrayList<>(); 
        File archivo = new File(RUTA_CSV);
        if(!archivo.exists()){
            System.out.println("este archivo no existe");
            return listaClientes;
        }
        try(FileReader fr = new FileReader(archivo);
              BufferedReader br =new BufferedReader(fr)){
            System.out.println("Listado de Clientes");
            String linea;   
            while((linea=br.readLine())!=null){
                String[] campos=linea.split(SEPARADOR);
                Cliente cliente=new Cliente();
                cliente.setCedula(Integer.parseInt(campos[0]));
                cliente.setNombre(campos[1]);
                cliente.setTelefono(Integer.parseInt(campos[2]));
                listaClientes.add(cliente);
            }
        }catch (IOException ex) {
            System.out.println("este archivo no se pudo leer");
        }   
        return listaClientes;
    }
    
    public boolean actualizarCliente(Cliente cliente){
        ArrayList<Cliente> listaClientes=leerClientes();
        boolean existeCliente=false;
        for(Cliente c: listaClientes){
               if(c.getCedula()==cliente.getCedula()){
                   c.setCedula(cliente.getCedula());
                   c.setNombre(cliente.getNombre());
                   c.setTelefono(cliente.getTelefono());
               existeCliente=true;
               }
            }
        if(existeCliente){
            return sobreEscribirArchivo(listaClientes);
        }else{
            System.out.println("Cliente no encontrado");
            return false;
        }
    }
    public boolean eliminarCliente(int cedula){
        ArrayList<Cliente> listaClientes=leerClientes();
        boolean existeCliente=false;
        existeCliente=listaClientes.removeIf(cliente -> cliente.getCedula()==cedula);
        if(existeCliente)
            return sobreEscribirArchivo(listaClientes);
        else{
            System.out.println("Cliente no encontrado");
            return false;
        }
    }
    private boolean sobreEscribirArchivo(ArrayList<Cliente> listaClientes){
        try(FileWriter fw=new FileWriter(RUTA_CSV);
            BufferedWriter bw =new BufferedWriter(fw)){
            for(Cliente cliente : listaClientes){
                String renglon=cliente.getCedula()+SEPARADOR
                               +cliente.getNombre()+SEPARADOR
                               +cliente.getTelefono();
                bw.write(renglon);
                bw.newLine();
            }
        }catch (IOException ex) {      
            return false;
        }
        return true;
    }
}
