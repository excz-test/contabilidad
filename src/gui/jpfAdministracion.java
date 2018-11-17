/*
 * Clase jpfAdministracion
 */
package gui;

import clases.Catalogo;
import clases.Funcionalidad;
import clases.ItemCatalogo;
import clases.Modulo;
import clases.Rol;
import clases.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import servicios.CargaParametros;
import utilidades.ManejoArchivos;
import utilidades.Notificaciones;

/**
 *
 * @author excz010715
 */
public class jpfAdministracion extends javax.swing.JPanel {

    private ArrayList<Catalogo> catalogos;
    private ArrayList<ItemCatalogo> itemCatalogos;
    private ArrayList<ItemCatalogo> itemCatalogo;
    private ArrayList<Usuario> listadoUsuarios;
    private ArrayList<Modulo> listadoModulos;
    private ArrayList<Rol> listadoRoles;
    private ArrayList<Funcionalidad> listadoFuncionalidades;
    
    CargaParametros<Catalogo> cargaParamCatalogo = new CargaParametros<>(Catalogo.class);
    CargaParametros<ItemCatalogo> cargaParamItemCat = new CargaParametros<>(ItemCatalogo.class);
    CargaParametros<Usuario> cargarParametrosUsuario = new CargaParametros<>(Usuario.class);
    CargaParametros<Modulo> cargaParametrosModulo = new CargaParametros<>(Modulo.class);
    CargaParametros<Rol> cargaParametrosRoles = new CargaParametros<>(Rol.class);
    CargaParametros<Funcionalidad> cargaParametrosFun= new CargaParametros<>(Funcionalidad.class);
    ManejoArchivos<Catalogo> manejoArchivoCat;
    ManejoArchivos<ItemCatalogo> manejoArchivoItemCat;
    ManejoArchivos<Usuario> manejoArchivoUsuario;
    private int indice;
    private int indiceItems;
    private String codigoCatalogo;
    private int indiceUsuario;
    jfNuevo jfData;
    Catalogo catalogo;
    ItemCatalogo itemCat;
    Usuario usuario;

    /**
     * Creates new form jpfAdministracion
     */
    public jpfAdministracion() {
        initComponents();
        catalogos = new ArrayList<>();
        itemCatalogos = new ArrayList<>();
        manejoArchivoCat = new ManejoArchivos<>(Catalogo.class);
        manejoArchivoItemCat = new ManejoArchivos<>(ItemCatalogo.class);
        manejoArchivoUsuario = new ManejoArchivos<>(Usuario.class);
        mostrarCatalogos();
        
        catalogo = new Catalogo();
        listadoUsuarios = (ArrayList<Usuario>) cargarParametrosUsuario.cargarDatos();
        listadoModulos = (ArrayList<Modulo>) cargaParametrosModulo.cargarDatos();
        listadoRoles = (ArrayList<Rol>) cargaParametrosRoles.cargarDatos();
        listadoFuncionalidades = (ArrayList<Funcionalidad>) cargaParametrosFun.cargarDatos();
        cargarUsuarios();
        cargarModulos();
        cargarRoles();
        cargarFuncionalidades();
        //Evento a Jtable
        jTableItemsCatalogo.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();

            public void mouseClicked(MouseEvent e) {
                int i = jTableItemsCatalogo.getSelectedRow();
                itemCat.setId(Integer.parseInt(jTableItemsCatalogo.getValueAt(i, 0).toString()));
                itemCat.setCodigo(jTableItemsCatalogo.getValueAt(i, 1).toString());
                itemCat.setNombre(jTableItemsCatalogo.getValueAt(i, 2).toString());
                itemCat.setDescripcion(jTableItemsCatalogo.getValueAt(i, 3).toString());
                itemCat.setEstado(jTableItemsCatalogo.getValueAt(i, 4).toString());
            }
        });
        //Evento Jtable Usuario
        /*jTableUsuario.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();

            public void mouseClicked(MouseEvent e) {
                int i = jTableUsuario.getSelectedRow();
                usuario.setCodigo(Integer.parseInt(jTableUsuario.getValueAt(i, 0).toString()));
                usuario.setNombres(jTableUsuario.getValueAt(i, 1).toString());
                usuario.setApellidos(jTableUsuario.getValueAt(i, 2).toString());
                usuario.setIdentificacion(jTableUsuario.getValueAt(i, 3).toString());
                usuario.setTelefono(jTableUsuario.getValueAt(i, 4).toString());
                usuario.setUsername(jTableUsuario.getValueAt(i, 5).toString());
                usuario.setPassword(jTableUsuario.getValueAt(i, 6).toString());
                usuario.setCorreo(jTableUsuario.getValueAt(i, 7).toString());
                usuario.setEstado(jTableUsuario.getValueAt(i, 8).toString());
                
            }
        });*/
    }

    public final void mostrarCatalogos() {
        catalogos = (ArrayList<Catalogo>) cargaParamCatalogo.cargarDatos();
        String[] array = new String[catalogos.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = catalogos.get(i).getNombre();
        }
        jListCatalogos.setListData(array);
    }

    public void editarCatalogo(int indice) {
        jfData = new jfNuevo();
        //Asignación de catalogo
        catalogo = catalogos.get(indice);
        //Asignación de código para cargar Items parámetros
        this.jfData.txtId.setText(catalogo.getId().toString());
        this.jfData.txtCodigo.setText(catalogo.getCodigo());
        this.jfData.txtNombre.setText(catalogo.getNombre());
        this.jfData.txtDescripcion.setText(catalogo.getDescripcion());
        boolean estado = false;
        if (catalogo.getEstado().equals("ACT")) {
            estado = true;
        }
        this.jfData.chkEstado.setSelected(estado);
        this.chkEstado.setSelected(estado);

        jfData.setVisible(true);
        jfData.pack();
        jfData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfData.setLocationRelativeTo(null);
        jfData.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //jfData.setName("Nuevo Catálogo");
        jfData.setResizable(false);

        cargarItemsCatalogo();

    }

    /**
     * Presenta items de una catálogo
     */
    public void cargarItemsCatalogo() {
        itemCatalogos = (ArrayList<ItemCatalogo>) cargaParamItemCat.cargarDatos();
        itemCatalogo = new ArrayList<>();
        for (ItemCatalogo item : itemCatalogos) {
            if (codigoCatalogo.equals(item.getCodigo())) {
                itemCatalogo.add(item);
            }
        }
        String[][] arrayCatalogo = new String[itemCatalogo.size()][5];
        for (int i = 0; i < itemCatalogo.size(); i++) {
            arrayCatalogo[i][0] = itemCatalogo.get(i).getId().toString();
            arrayCatalogo[i][1] = itemCatalogo.get(i).getCodigo();
            arrayCatalogo[i][2] = itemCatalogo.get(i).getNombre();
            arrayCatalogo[i][3] = itemCatalogo.get(i).getDescripcion();
            arrayCatalogo[i][4] = itemCatalogo.get(i).getEstado();
        }
        jTableItemsCatalogo.setModel(new DefaultTableModel(
                arrayCatalogo,
                new String[]{
                    "Item Nro.", "Código catálogo", "Nombre", "Descripción", "Estado"
                }
        ));
    }
    
    /**
     * Presentar usuarios
     */
    public void cargarUsuarios() {
        String [][] arrayUsuarios = new String[listadoUsuarios.size()][9];
        for (int i = 0; i < listadoUsuarios.size(); i++) {
            arrayUsuarios[i][0] = listadoUsuarios.get(i).getCodigo().toString();
            arrayUsuarios[i][1] = listadoUsuarios.get(i).getNombres();
            arrayUsuarios[i][2] = listadoUsuarios.get(i).getApellidos();
            arrayUsuarios[i][3] = listadoUsuarios.get(i).getIdentificacion();
            arrayUsuarios[i][4] = listadoUsuarios.get(i).getTelefono();
            arrayUsuarios[i][5] = listadoUsuarios.get(i).getUsername();
            arrayUsuarios[i][6] = listadoUsuarios.get(i).getPassword();
            arrayUsuarios[i][7] = listadoUsuarios.get(i).getCorreo();
            arrayUsuarios[i][8] = listadoUsuarios.get(i).getEstado();
        }
        jTableUsuario.setModel(new DefaultTableModel(
                arrayUsuarios,
                new String[]{
                    "Código", "Nombre", "Apellido", "Identificación", 
                    "Teléfono", "Username", "Password", " Correo", "Estado"
                }));
    }
    
    /**
     * presenta Módulos disponibles
     * 
     */
    public void cargarModulos() {
        String [][] arrayModulos = new String [listadoModulos.size()][4];
        for (int i = 0; i < listadoModulos.size(); i++) {
            arrayModulos[i][0] = listadoModulos.get(i).getId().toString();
            arrayModulos[i][1] = listadoModulos.get(i).getNombre();
            arrayModulos[i][2] = listadoModulos.get(i).getDescripcion();
            arrayModulos[i][3] = listadoModulos.get(i).getEstado();
        }
        
        jTableModulos.setModel(new DefaultTableModel(
                arrayModulos,
                new String []{
                    "Id", "Módulo", "Descripción", "Estado" 
                }
        
        ));
    }
    /**
     * Cargar funcionalidades
     * 
     */
    public void cargarFuncionalidades() {
        String [][] arrayFun = new String[listadoFuncionalidades.size()][5];
        System.out.println("gui.jpfAdministracion.cargarFuncionalidades()"+listadoFuncionalidades.size());
        for (int i = 0; i < listadoFuncionalidades.size(); i++) {
            arrayFun[i][0] = listadoFuncionalidades.get(i).getId().toString();
            arrayFun[i][1] = listadoFuncionalidades.get(i).getCodigo();
            arrayFun[i][2] = listadoFuncionalidades.get(i).getNombre();
            arrayFun[i][3] = listadoFuncionalidades.get(i).getDescripcion();
            arrayFun[i][4] = listadoFuncionalidades.get(i).getEstado();
        }
        jTableFuncionalidades.setModel(new DefaultTableModel(
                arrayFun,
                new String []{
                    "Id", "Código funcionalidad", "Nombre", "Descripción", "Estado"
                }
        ));
    }
    /**
     * Cargar roles
     * private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String estado;
     */
    
    public void cargarRoles() {
        String [][] arrayRoles = new String [listadoRoles.size()][5];
        for (int i = 0; i < listadoRoles.size(); i++) {
            arrayRoles[i][0] = listadoRoles.get(i).getId().toString();
            arrayRoles[i][1] = listadoRoles.get(i).getCodigo();
            arrayRoles[i][2] = listadoRoles.get(i).getNombre();
            arrayRoles[i][3] = listadoRoles.get(i).getDescripcion();
            arrayRoles[i][4] = listadoRoles.get(i).getEstado();
        }
        
        jTableRoles.setModel(new DefaultTableModel(
                arrayRoles,
                new String [] {
                    "Id", "Código rol", "Nombre", "Descripción", "Estado"
                }
        ));
    }

    /*private void limpiarCampos() {
        this.txtCodigo.setText(null);
        this.txtId.setText(null);
        this.txtNombre.setText(null);
        this.txtDescripcion.setText(null);
        this.chkEstado.setSelected(false);
    }*/
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(String codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public int getIndiceItems() {
        return indiceItems;
    }

    public void setIndiceItems(int indiceItems) {
        this.indiceItems = indiceItems;
    }

    public ArrayList<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(ArrayList<Usuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public int getIndiceUsuario() {
        return indiceUsuario;
    }

    public void setIndiceUsuario(int indiceUsuario) {
        this.indiceUsuario = indiceUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListCatalogos = new javax.swing.JList<>();
        panel1 = new java.awt.Panel();
        lbl1 = new java.awt.Label();
        txtId = new javax.swing.JTextField();
        lbl2 = new java.awt.Label();
        txtCodigo = new javax.swing.JTextField();
        lbl3 = new java.awt.Label();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        lbl4 = new java.awt.Label();
        lbl5 = new java.awt.Label();
        chkEstado = new javax.swing.JCheckBox();
        btnNuevoCatalogo = new javax.swing.JButton();
        btnModCat = new javax.swing.JButton();
        btnEliminarCat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemsCatalogo = new javax.swing.JTable();
        btnDeleteItem = new java.awt.Button();
        btnAddItem = new java.awt.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsuario = new javax.swing.JTable();
        btnAddUsuario = new javax.swing.JButton();
        btnDeleteUsuario = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableModulos = new javax.swing.JTable();
        btnAddUsuario1 = new javax.swing.JButton();
        btnDeleteUsuario1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableRoles = new javax.swing.JTable();
        btnAddUsuario2 = new javax.swing.JButton();
        btnDeleteUsuario2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableFuncionalidades = new javax.swing.JTable();
        btnAddUsuario3 = new javax.swing.JButton();
        btnDeleteUsuario3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jListCatalogos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCatalogosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListCatalogos);

        panel1.setName("Editar"); // NOI18N

        lbl1.setText("*Id");

        txtId.setEnabled(false);

        lbl2.setText("*Código");

        txtCodigo.setEnabled(false);

        lbl3.setText("*Nombre");

        txtNombre.setEnabled(false);

        txtDescripcion.setEnabled(false);

        lbl4.setText("Descripción");

        lbl5.setText("*Estado");

        chkEstado.setEnabled(false);
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });

        btnNuevoCatalogo.setText("Nuevo");
        btnNuevoCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCatalogoActionPerformed(evt);
            }
        });

        btnModCat.setText("Modificar");
        btnModCat.setEnabled(false);
        btnModCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModCatActionPerformed(evt);
            }
        });

        btnEliminarCat.setText("Eliminar");
        btnEliminarCat.setEnabled(false);
        btnEliminarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Edición de catálogos");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkEstado)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(btnNuevoCatalogo)
                                .addGap(6, 6, 6)
                                .addComponent(btnModCat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarCat))
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkEstado))
                .addGap(10, 10, 10)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnNuevoCatalogo)
                    .addComponent(btnModCat)
                    .addComponent(btnEliminarCat))
                .addGap(9, 9, 9))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Listado catálogos disponibles:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Items de catálogo:");

        jTableItemsCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableItemsCatalogo.setColumnSelectionAllowed(true);
        jTableItemsCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableItemsCatalogoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableItemsCatalogo);
        jTableItemsCatalogo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnDeleteItem.setEnabled(false);
        btnDeleteItem.setLabel("-");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        btnAddItem.setEnabled(false);
        btnAddItem.setLabel("+");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddItem, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(btnDeleteItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        panel1.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Gestión de Catálogos", jPanel1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Listado usuarios:");

        jTableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableUsuario);

        btnAddUsuario.setText("+");
        btnAddUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUsuarioActionPerformed(evt);
            }
        });

        btnDeleteUsuario.setText("-");
        btnDeleteUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(344, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", jPanel2);

        jPanel3.setName("Items"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Módulos disponibles:");

        jTableModulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableModulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableModulosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableModulos);

        btnAddUsuario1.setText("+");
        btnAddUsuario1.setEnabled(false);
        btnAddUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUsuario1ActionPerformed(evt);
            }
        });

        btnDeleteUsuario1.setText("-");
        btnDeleteUsuario1.setEnabled(false);
        btnDeleteUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUsuario1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddUsuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAddUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(344, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Módulos", jPanel3);

        jPanel4.setName("Items"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Roles disponibles:");

        jTableRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRolesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableRoles);

        btnAddUsuario2.setText("+");
        btnAddUsuario2.setEnabled(false);
        btnAddUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUsuario2ActionPerformed(evt);
            }
        });

        btnDeleteUsuario2.setText("-");
        btnDeleteUsuario2.setEnabled(false);
        btnDeleteUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUsuario2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddUsuario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAddUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(344, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Roles", jPanel4);

        jPanel5.setName("Items"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Funcionalidades");

        jTableFuncionalidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableFuncionalidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionalidadesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableFuncionalidades);

        btnAddUsuario3.setText("+");
        btnAddUsuario3.setEnabled(false);
        btnAddUsuario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUsuario3ActionPerformed(evt);
            }
        });

        btnDeleteUsuario3.setText("-");
        btnDeleteUsuario3.setEnabled(false);
        btnDeleteUsuario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUsuario3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddUsuario3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAddUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(344, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Funcionalidades", jPanel5);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Control general de parámetros y catálogos del sistema de Contabilidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(339, 339, 339))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCatActionPerformed
        catalogo = catalogos.get(getIndice());
        System.out.println("gui.jpfAdministracion.btnEliminarCatActionPerformed()" + catalogos.size());
        if (Notificaciones.mensajeConfirmacion("Eliminar registro", "Se va a eliminar el catálogo (" + catalogo.getNombre() + ") \nDesea continuar?")) {
            for (Catalogo catalogo1 : catalogos) {
                if (catalogo1.getId().equals(catalogo.getId())) {
                    catalogos.remove(catalogo1);
                    System.out.println("gui.jpfAdministracion.btnEliminarCatActionPerformed()" + catalogos.size());
                    break;
                }
            }

            manejoArchivoCat.deleteArchivo(catalogo);
            for (Catalogo catalogo1 : catalogos) {
                manejoArchivoCat.registrar(catalogo1);
            }

            mostrarCatalogos();
        }

    }//GEN-LAST:event_btnEliminarCatActionPerformed

    private void btnModCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModCatActionPerformed
        editarCatalogo(getIndice());
    }//GEN-LAST:event_btnModCatActionPerformed

    private void chkEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstadoActionPerformed

    private void jListCatalogosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCatalogosMouseClicked
        this.btnEliminarCat.setEnabled(true);
        this.btnModCat.setEnabled(true);
        this.btnAddItem.setEnabled(true);
        setIndice(jListCatalogos.getSelectedIndex());
        catalogo = catalogos.get(getIndice());
        setCodigoCatalogo(catalogo.getCodigo());
        this.txtCodigo.setText(catalogo.getCodigo());
        this.txtNombre.setText(catalogo.getNombre());
        this.txtDescripcion.setText(catalogo.getDescripcion());
        this.txtId.setText(catalogo.getId().toString());
        boolean estado = false;
        if (catalogo.getEstado().equals("ACT")) {
            estado = true;
        }
        this.chkEstado.setSelected(estado);
        cargarItemsCatalogo();
    }//GEN-LAST:event_jListCatalogosMouseClicked

    private void btnNuevoCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCatalogoActionPerformed
        jfData = new jfNuevo();
        jfData.setVisible(true);
        jfData.pack();
        jfData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfData.setLocationRelativeTo(null);
        jfData.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //jfData.setName("Nuevo Catálogo");
        jfData.setResizable(false);
        catalogo = new Catalogo();
        /*
        Catalogo catNuevo = new Catalogo();
        int id = 0;
        if ("".equals(this.txtId.getText())
                || "".equals(this.txtCodigo.getText())
                || "".equals(this.txtNombre.getText())) {
            Notificaciones.mensajeInformativo("Los campos son obligatorios");
        } else {
            try {
                id = Integer.parseInt(this.txtId.getText());
                String codigoCat = this.txtCodigo.getText();
                String nombreCat = this.txtNombre.getText();
                String descripcionCat = this.txtDescripcion.getText();
                String estadoCat = this.chkEstado.equals(true) ? "ACT" : "INA";
                Catalogo catalogo = new Catalogo(id, codigoCat, nombreCat, descripcionCat, estadoCat);
                //System.err.println(catalogo.toString());
                manejoArchivoCat.registrar(catalogo);
                mostrarCatalogos();
                limpiarCampos();
            } catch (NumberFormatException e) {
                Notificaciones.mensajeInformativo("El id del catálogo debe ser un número");
                System.err.println("Error al convertir a número. " + Arrays.toString(e.getStackTrace()));
            }
        }*/
    }//GEN-LAST:event_btnNuevoCatalogoActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        //itemCat = new ItemCatalogo();
        try {
            Integer id = Integer.parseInt(Notificaciones.inputDialog("Id de Item"));
            String codigo = catalogo.getCodigo();
            String nombre = Notificaciones.inputDialog("Ingrese el nombre del Item");
            String descripcion = Notificaciones.inputDialog("Ingrese una descripción para el item (Opcional)");
            itemCat = new ItemCatalogo(id, codigo, nombre, descripcion, codigo);
            manejoArchivoItemCat.registrar(itemCat);
            cargarItemsCatalogo();
        } catch (NumberFormatException e) {
            Notificaciones.mensajeInformativo("Debe ingresar números como identificador");
        }
        
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void jTableItemsCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableItemsCatalogoMouseClicked
        this.btnDeleteItem.setEnabled(true);
    }//GEN-LAST:event_jTableItemsCatalogoMouseClicked

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        if (Notificaciones.mensajeConfirmacion("Eliminar registro", "Se va a eliminar el catálogo (" + itemCat.getNombre() + ") \nDesea continuar?")) {
            for (int i = 0; i < itemCatalogos.size(); i++) {
                if (Objects.equals(itemCatalogos.get(i).getId(), itemCat.getId())) {
                    itemCatalogos.remove(i);
                    break;
                }
            }
            manejoArchivoItemCat.deleteArchivo(itemCat);
            for (ItemCatalogo itemCatalogo1 : itemCatalogos) {
                manejoArchivoItemCat.registrar(itemCatalogo1);
            }
            cargarItemsCatalogo();
        }

    }//GEN-LAST:event_btnDeleteItemActionPerformed

    private void btnAddUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUsuarioActionPerformed
        try {
            Integer id = Integer.parseInt(Notificaciones.inputDialog("Id de Usuario"));
            String nombre = Notificaciones.inputDialog("Nombres");
            String apellido = Notificaciones.inputDialog("Apellidos");
            String identificacion = Notificaciones.inputDialog("Identificación (1712345678)");
            String telefono = Notificaciones.inputDialog("Teléfono");
            String username = Notificaciones.inputDialog("USERNAME");
            String password = Notificaciones.inputDialog("PASSSWORD");
            String correo = Notificaciones.inputDialog("Correo");
            String estado = Notificaciones.inputDialog("Estado Activo = ACT; Inactivo = INA");
            usuario = new Usuario(id, nombre, apellido, identificacion, telefono, username, password, correo, estado);
            //Linea para ejecucion en linea
            listadoUsuarios.add(usuario);
            //Línea para guardar en fichero
            manejoArchivoUsuario.registrar(usuario);
            cargarUsuarios();
        } catch (NumberFormatException e) {
            Notificaciones.mensajeInformativo("Debe ingresar números como identificador");
        }
    }//GEN-LAST:event_btnAddUsuarioActionPerformed

    private void btnDeleteUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUsuarioActionPerformed
        if (Notificaciones.mensajeConfirmacion("Eliminar registro", "Se va a eliminar el usuario (" + usuario.getNombres() + ") \nDesea continuar?")) {
            listadoUsuarios.remove(usuario);
            cargarUsuarios();
            /*manejoArchivoUsuario.deleteArchivo(usuario);
            for (Usuario user : listadoUsuarios) {
                manejoArchivoUsuario.registrar(user);
            }
            cargarUsuarios();*/
        }
    }//GEN-LAST:event_btnDeleteUsuarioActionPerformed

    private void jTableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuarioMouseClicked
        //Cuando hace click en usuarios
        setIndiceUsuario(Integer.parseInt(jTableUsuario.getModel().getValueAt(jTableUsuario.getSelectedRow(), 0).toString()));
        usuario = listadoUsuarios.get(getIndiceUsuario());
        
    }//GEN-LAST:event_jTableUsuarioMouseClicked

    private void jTableModulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableModulosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableModulosMouseClicked

    private void btnAddUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddUsuario1ActionPerformed

    private void btnDeleteUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteUsuario1ActionPerformed

    private void jTableRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRolesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableRolesMouseClicked

    private void btnAddUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUsuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddUsuario2ActionPerformed

    private void btnDeleteUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUsuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteUsuario2ActionPerformed

    private void jTableFuncionalidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionalidadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFuncionalidadesMouseClicked

    private void btnAddUsuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUsuario3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddUsuario3ActionPerformed

    private void btnDeleteUsuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUsuario3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteUsuario3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAddItem;
    private javax.swing.JButton btnAddUsuario;
    private javax.swing.JButton btnAddUsuario1;
    private javax.swing.JButton btnAddUsuario2;
    private javax.swing.JButton btnAddUsuario3;
    private java.awt.Button btnDeleteItem;
    private javax.swing.JButton btnDeleteUsuario;
    private javax.swing.JButton btnDeleteUsuario1;
    private javax.swing.JButton btnDeleteUsuario2;
    private javax.swing.JButton btnDeleteUsuario3;
    private javax.swing.JButton btnEliminarCat;
    private javax.swing.JButton btnModCat;
    private javax.swing.JButton btnNuevoCatalogo;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListCatalogos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableFuncionalidades;
    private javax.swing.JTable jTableItemsCatalogo;
    private javax.swing.JTable jTableModulos;
    private javax.swing.JTable jTableRoles;
    private javax.swing.JTable jTableUsuario;
    private java.awt.Label lbl1;
    private java.awt.Label lbl2;
    private java.awt.Label lbl3;
    private java.awt.Label lbl4;
    private java.awt.Label lbl5;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
