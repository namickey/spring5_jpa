package demo.service;

import demo.entity.Member;
import demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl {

    @Autowired
    MemberRepository memberRepository;

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

    public void find() {
        List<Member> list = memberRepository.findAll();
        for (Member member : list){
            System.out.println(member);
        }
    }
}
