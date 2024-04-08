package ch._42lausanne.swingy.repository;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
}
