package dev.danvega.streamsschedule.controller;

import com.google.protobuf.Timestamp;
import dev.danvega.streamsschedule.exception.LiveStreamNotFoundException;
import dev.danvega.streamsschedule.model.LiveStream;
import dev.danvega.streamsschedule.repository.LiveStreamRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.techtter.grpc.GreeterGrpc;
import net.techtter.grpc.Helloworld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/streams")
public class LiveStreamController {

    private final LiveStreamRepository repository;

    @Autowired
    public LiveStreamController(LiveStreamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<LiveStream> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public LiveStream findById(@PathVariable String id) throws LiveStreamNotFoundException {
        return repository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody LiveStream stream) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        GreeterGrpc.GreeterBlockingStub clientStub = GreeterGrpc.newBlockingStub(channel);
        String firstName = stream.getFirstName();
        String lastName = stream.getLastName();
        String dob = stream.getDob();
        String email = stream.getEmail();
        String phone = stream.getPhone();

        Instant currentTime = Instant.now();
        com.google.protobuf.Timestamp timestamp = Timestamp.newBuilder().setSeconds(currentTime.getEpochSecond()).setNanos(currentTime.getNano()).build();
        Helloworld.HelloRequest request = Helloworld.HelloRequest.newBuilder().setName(firstName).setLastname(lastName).setDob(dob).setEmail(email).setPhone(phone).setTimeStamp(timestamp).build();
        clientStub.sayHello(request);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody LiveStream stream, @PathVariable String id) {
        repository.update(stream,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

}
