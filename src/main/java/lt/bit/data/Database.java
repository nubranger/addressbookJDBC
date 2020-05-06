package lt.bit.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Person> getPersons(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select * from person")) {

            List<Person> ret = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setBirthDate(rs.getDate("birth_date"));
                p.setSalary(rs.getBigDecimal("salary"));

                ret.add(p);
            }
            return ret;
        }
    }

    public static List<Address> getPersonsAddresses(Connection conn, Integer person_id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from address where person_id=?")) {
            List<Address> ret = new ArrayList<>();


            if (person_id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, person_id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Address a = new Address();
                    a.setId(rs.getInt("id"));
                    a.setAddress(rs.getString("address"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));

                    ret.add(a);
                }
            }
            return ret;
        }
    }

    public static List<Contact> getPersonsContacts(Connection conn, Integer person_id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from contact where person_id=?")) {
            List<Contact> ret = new ArrayList<>();


            if (person_id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, person_id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Contact c = new Contact();
                    c.setId(rs.getInt("id"));
                    c.setContact(rs.getString("contact"));
                    c.setType(rs.getString("contact_type"));

                    ret.add(c);
                }
            }
            return ret;
        }
    }

    public static Person getPerson(Connection conn, Integer id) throws SQLException {
        Person p = new Person();
        try (PreparedStatement pst = conn.prepareStatement("select * from person where id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    p.setId(rs.getInt("id"));
                    p.setFirstName(rs.getString("first_name"));
                    p.setLastName(rs.getString("last_name"));
                    p.setBirthDate(rs.getDate("birth_date"));
                    p.setSalary(rs.getBigDecimal("salary"));

                }
            }
        }
        return p;
    }

    public static Address getAddress(Connection conn, Integer id) throws SQLException {
        Address a = new Address();
        try (PreparedStatement pst = conn.prepareStatement("select * from address where id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    a.setId(rs.getInt("id"));
                    a.setAddress(rs.getString("address"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));

                }
            }
        }
        return a;
    }

    public static Contact getContact(Connection conn, Integer id) throws SQLException {
        Contact c = new Contact();
        try (PreparedStatement pst = conn.prepareStatement("select * from contact where id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    c.setId(rs.getInt("id"));
                    c.setContact(rs.getString("contact"));
                    c.setType(rs.getString("contact_type"));
                }
            }
        }
        return c;
    }

    public static void addPerson(Connection conn, Person p) throws SQLException {
        List<Person> personList = getPersons(conn);
        if (!personList.contains(p)) {

            String sql = "insert into person (first_name, last_name, birth_date, salary) "
                    + "values(?,?,?,?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, p.getFirstName());
                pst.setString(2, p.getLastName());
                if (p.getBirthDate() == null) {
                    pst.setNull(3, Types.DATE);
                } else {
                    java.sql.Date bdate = new java.sql.Date(p.getBirthDate().getTime());
                    pst.setDate(3, bdate);
                }
                if (p.getSalary() == null) {
                    pst.setNull(4, Types.DECIMAL);
                } else {
                    pst.setBigDecimal(4, p.getSalary());
                }
                pst.execute();
            }

        }
    }

    public static void updatePerson(Connection conn, Integer id, Person p) throws SQLException {

        String sql = "update person set first_name = ?, last_name = ?, birth_date = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, p.getFirstName());
            pst.setString(2, p.getLastName());

            if (p.getBirthDate() == null) {
                pst.setNull(3, Types.DATE);
            } else {
                java.sql.Date bdate = new java.sql.Date(p.getBirthDate().getTime());
                pst.setDate(3, bdate);
            }
            if (p.getSalary() == null) {
                pst.setNull(4, Types.DECIMAL);
            } else {
                pst.setBigDecimal(4, p.getSalary());
            }

            if (id == null) {
                pst.setNull(5, Types.INTEGER);
            } else {
                pst.setInt(5, id);
            }

            pst.execute();
            System.out.println(pst);
        }
    }

    public static void updateAddress(Connection conn, Integer id, Address a) throws SQLException {

        String sql = "update address set address = ?, city = ?, postal_code = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, a.getAddress());
            pst.setString(2, a.getCity());
            pst.setString(3, a.getPostalCode());

            if (id == null) {
                pst.setNull(4, Types.INTEGER);
            } else {
                pst.setInt(4, id);
            }

            pst.execute();
            System.out.println(pst);
        }
    }

    public static void updateContact(Connection conn, Integer id, Contact c) throws SQLException {

        String sql = "update contact set contact = ?, contact_type = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, c.getContact());
            pst.setString(2, c.getType());

            if (id == null) {
                pst.setNull(3, Types.INTEGER);
            } else {
                pst.setInt(3, id);
            }

            pst.execute();
            System.out.println(pst);
        }
    }

    public static void delPerson(Connection conn, Integer id) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("delete from person where id = ? ");
        pst.setInt(1, id);
        pst.execute();
    }

    public static void delAddress(Connection conn, Integer id) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("delete from address where id = ? ");
        pst.setInt(1, id);
        pst.execute();
    }

    public static void delContact(Connection conn, Integer id) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("delete from contact where id = ? ");
        pst.setInt(1, id);
        pst.execute();
    }


    public static List<Address> getAddresses(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select*from address")) {
            List<Address> ret = new ArrayList<>();
            while (rs.next()) {
                Address a = new Address();
                a.setAddress(rs.getString("address"));
                a.setCity(rs.getString("city"));
                a.setPostalCode(rs.getString("postal_code"));
                ret.add(a);
            }
            return ret;
        }
    }


    public static List<Contact> getContacts(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select*from contact")) {
            List<Contact> ret = new ArrayList<>();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setType(rs.getString("contact_type"));
                c.setContact(rs.getString("contact"));
                ret.add(c);
            }
            return ret;
        }

    }


    public static void addAddress(Connection conn, Integer person_id, Address a) throws SQLException {

        List<Address> addressList = getAddresses(conn);
        if (!addressList.contains(a)) {
            String sql = "insert into address (person_id, address, city, postal_code) "
                    + "values(?,?,?,?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, person_id);
                pst.setString(2, a.getAddress());
                pst.setString(3, a.getCity());
                pst.setString(4, a.getPostalCode());
                pst.execute();

                System.out.println(pst);
            }
        }
    }

    public static void addContact(Connection conn, Integer person_id, Contact c) throws SQLException {

        List<Contact> contactList = getContacts(conn);
        if (!contactList.contains(c)) {
            String sql = "insert into contact (person_id, contact, contact_type) "
                    + "values(?,?,?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, person_id);
                pst.setString(2, c.getContact());
                pst.setString(3, c.getType());
                pst.execute();

                System.out.println(pst);
            }
        }
    }
}







