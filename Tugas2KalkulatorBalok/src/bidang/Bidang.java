/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidang;

/**
 *
 * @author Lenovo
 */
public interface Bidang  {
    
    double MenghitungLuas();
    double MenghitungKeliling();
    
}


-----
    import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface LombaCeritaDAO {
    void create(LombaCerita lombaCerita);
    List<LombaCerita> readAll();
    LombaCerita readById(int id);
    void update(LombaCerita lombaCerita);
    void delete(int id);
    List<LombaCerita> search(String keyword);
}

class LombaCerita {
    private int id;
    private String judul;
    private double nilaiAlurCerita;
    private double nilaiOrisinalitas;
    private double nilaiPemilihanKata;
    private double nilaiAkhir;

    public LombaCerita(int id, String judul, double nilaiAlurCerita, double nilaiOrisinalitas, double nilaiPemilihanKata) {
        this.id = id;
        this.judul = judul;
        this.nilaiAlurCerita = nilaiAlurCerita;
        this.nilaiOrisinalitas = nilaiOrisinalitas;
        this.nilaiPemilihanKata = nilaiPemilihanKata;
        this.nilaiAkhir = (nilaiAlurCerita + nilaiOrisinalitas + nilaiPemilihanKata) / 3;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public double getNilaiAlurCerita() {
        return nilaiAlurCerita;
    }

    public double getNilaiOrisinalitas() {
        return nilaiOrisinalitas;
    }

    public double getNilaiPemilihanKata() {
        return nilaiPemilihanKata;
    }

    public double getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setNilaiAlurCerita(double nilaiAlurCerita) {
        this.nilaiAlurCerita = nilaiAlurCerita;
        this.nilaiAkhir = (nilaiAlurCerita + nilaiOrisinalitas + nilaiPemilihanKata) / 3;
    }

    public void setNilaiOrisinalitas(double nilaiOrisinalitas) {
        this.nilaiOrisinalitas = nilaiOrisinalitas;
        this.nilaiAkhir = (nilaiAlurCerita + nilaiOrisinalitas + nilaiPemilihanKata) / 3;
    }

    public void setNilaiPemilihanKata(double nilaiPemilihanKata) {
        this.nilaiPemilihanKata = nilaiPemilihanKata;
        this.nilaiAkhir = (nilaiAlurCerita + nilaiOrisinalitas + nilaiPemilihanKata) / 3;
    }
}

class LombaCeritaDAOImpl implements LombaCeritaDAO {
    private Connection connection;

    public LombaCeritaDAOImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/db_lomba", "username", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(LombaCerita lombaCerita) {
        try {
            String query = "INSERT INTO lomba_cerita (judul, alur_cerita, orisinalitas, pemilihan_kata) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lombaCerita.getJudul());
            statement.setDouble(2, lombaCerita.getNilaiAlurCerita());
            statement.setDouble(3, lombaCerita.getNilaiOrisinalitas());
            statement.setDouble(4, lombaCerita.getNilaiPemilihanKata());
            statement.executeUpdate();
            System.out.println("Data berhasil ditambahkan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LombaCerita> readAll() {
        List<LombaCerita> lombaCeritaList = new ArrayList<>();
        try {
            String query = "SELECT * FROM lomba_cerita";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                double alurCerita = resultSet.getDouble("alur_cerita");
                double orisinalitas = resultSet.getDouble("orisinalitas");
                double pemilihanKata = resultSet.getDouble("pemilihan_kata");
                LombaCerita lombaCerita = new LombaCerita(id, judul, alurCerita, orisinalitas, pemilihanKata);
                lombaCeritaList.add(lombaCerita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lombaCeritaList;
    }

    @Override
    public LombaCerita readById(int id) {
        LombaCerita lombaCerita = null;
        try {
            String query = "SELECT * FROM lomba_cerita WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String judul = resultSet.getString("judul");
                double alurCerita = resultSet.getDouble("alur_cerita");
                double orisinalitas = resultSet.getDouble("orisinalitas");
                double pemilihanKata = resultSet.getDouble("pemilihan_kata");
                lombaCerita = new LombaCerita(id, judul, alurCerita, orisinalitas, pemilihanKata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lombaCerita;
    }

    @Override
    public void update(LombaCerita lombaCerita) {
        try {
            String query = "UPDATE lomba_cerita SET judul = ?, alur_cerita = ?, orisinalitas = ?, pemilihan_kata = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lombaCerita.getJudul());
            statement.setDouble(2, lombaCerita.getNilaiAlurCerita());
            statement.setDouble(3, lombaCerita.getNilaiOrisinalitas());
            statement.setDouble(4, lombaCerita.getNilaiPemilihanKata());
            statement.setInt(5, lombaCerita.getId());
            statement.executeUpdate();
            System.out.println("Data berhasil diperbarui");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM lomba_cerita WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Data berhasil dihapus");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LombaCerita> search(String keyword) {
        List<LombaCerita> lombaCeritaList = new ArrayList<>();
        try {
            String query = "SELECT * FROM lomba_cerita WHERE judul LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                double alurCerita = resultSet.getDouble("alur_cerita");
                double orisinalitas = resultSet.getDouble("orisinalitas");
                double pemilihanKata = resultSet.getDouble("pemilihan_kata");
                LombaCerita lombaCerita = new LombaCerita(id, judul, alurCerita, orisinalitas, pemilihanKata);
                lombaCeritaList.add(lombaCerita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lombaCeritaList;
    }
}

public class LombaCeritaApp {
    public static void main(String[] args) {
        LombaCeritaDAO lombaCeritaDAO = new LombaCeritaDAOImpl();

        // Menambahkan data
        LombaCerita lombaCerita1 = new LombaCerita(1, "Judul 1", 8.5, 7.5, 9.0);
        lombaCeritaDAO.create(lombaCerita1);

        // Membaca semua data
        List<LombaCerita> lombaCeritaList = lombaCeritaDAO.readAll();
        for (LombaCerita lombaCerita : lombaCeritaList) {
            System.out.println("ID: " + lombaCerita.getId());
            System.out.println("Judul: " + lombaCerita.getJudul());
            System.out.println("Nilai Alur Cerita: " + lombaCerita.getNilaiAlurCerita());
            System.out.println("Nilai Orisinalitas: " + lombaCerita.getNilaiOrisinalitas());
            System.out.println("Nilai Pemilihan Kata: " + lombaCerita.getNilaiPemilihanKata());
            System.out.println("Nilai Akhir: " + lombaCerita.getNilaiAkhir());
            System.out.println("------------------------");
        }

        // Membaca data berdasarkan ID
        LombaCerita lombaCerita2 = lombaCeritaDAO.readById(2);
        if (lombaCerita2 != null) {
            System.out.println("ID: " + lombaCerita2.getId());
            System.out.println("Judul: " + lombaCerita2.getJudul());
            System.out.println("Nilai Alur Cerita: " + lombaCerita2.getNilaiAlurCerita());
            System.out.println("Nilai Orisinalitas: " + lombaCerita2.getNilaiOrisinalitas());
            System.out.println("Nilai Pemilihan Kata: " + lombaCerita2.getNilaiPemilihanKata());
            System.out.println("Nilai Akhir: " + lombaCerita2.getNilaiAkhir());
            System.out.println("------------------------");
        } else {
            System.out.println("Data tidak ditemukan");
        }

        // Mengupdate data
        LombaCerita lombaCerita3 = lombaCeritaDAO.readById(3);
        if (lombaCerita3 != null) {
            lombaCerita3.setJudul("Judul 3 (Updated)");
            lombaCeritaDAO.update(lombaCerita3);
            System.out.println("Data berhasil diperbarui");
        } else {
            System.out.println("Data tidak ditemukan");
        }

        // Menghapus data
        lombaCeritaDAO.delete(4);
        System.out.println("Data berhasil dihapus");

        // Mencari data berdasarkan judul
        List<LombaCerita> searchResults = lombaCeritaDAO.search("cerita");
        for (LombaCerita result : searchResults) {
            System.out.println("ID: " + result.getId());
            System.out.println("Judul: " + result.getJudul());
            System.out.println("Nilai Alur Cerita: " + result.getNilaiAlurCerita());
            System.out.println("Nilai Orisinalitas: " + result.getNilaiOrisinalitas());
            System.out.println("Nilai Pemilihan Kata: " + result.getNilaiPemilihanKata());
            System.out.println("Nilai Akhir: " + result.getNilaiAkhir());
            System.out.println("------------------------");
        }
    }
}
