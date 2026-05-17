import java.util.*;

public class MemberDAO {
    private List<Member> members = new ArrayList<>();

    public void addMember(Member m) {
        members.add(m);
    }

    public Member findById(String id) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Member> findAll() {
        return members;
    }
}