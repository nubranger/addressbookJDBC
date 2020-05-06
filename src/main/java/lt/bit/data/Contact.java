package lt.bit.data;

public class Contact {

   private Integer id;
   private String type;
   private String contact;
   private Integer personId;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getContact() {
      return contact;
   }

   public void setContact(String contact) {
      this.contact = contact;
   }

   public Integer getPersonId() {
      return personId;
   }

   public void setPersonId(Integer personId) {
      this.personId = personId;
   }

   @Override
   public String toString() {
      return "Contact{" +
              "id=" + id +
              ", type='" + type + '\'' +
              ", contact='" + contact + '\'' +
              ", personId=" + personId +
              '}';
   }
}
