package jinheung.project.base.service;

import jinheung.project.base.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;


    public void test() {
        testRepository.saveTest("a", "b");
        System.out.println(testRepository.get("a"));
    }

}
