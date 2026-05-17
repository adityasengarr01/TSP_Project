public class Member {
    private String memberId;
    private String name;
    private String email;
    private String department;
    private String year;
    private String phone;

    public Member(String memberId, String name, String email,
                  String department, String year, String phone) {
        this.memberId   = memberId;
        this.name       = name;
        this.email      = email;
        this.department = department;
        this.year       = year;
        this.phone      = phone;
    }

    public String getMemberId()   { return memberId; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }
    public String getDepartment() { return department; }
    public String getYear()       { return year; }
    public String getPhone()      { return phone; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s %s",
            memberId, name, email, department, year);
    }
}