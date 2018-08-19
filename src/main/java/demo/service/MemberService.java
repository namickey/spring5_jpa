package demo.service;

import demo.entity.Member;
import demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public List<Member> find() {
        List<Member> list = memberRepository.findJoin();
        list.forEach(System.out::println);
        return list;
    }

    @Transactional
    public void resist(String name) {
        Member member = new Member();
        member.setName(name);
        memberRepository.save(member);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void resistError(String name) {
        Member member = new Member();
        member.setName(name);
        memberRepository.save(member);

        if (name != null) {
            throw new RuntimeException("some error.");
        }
    }
}
