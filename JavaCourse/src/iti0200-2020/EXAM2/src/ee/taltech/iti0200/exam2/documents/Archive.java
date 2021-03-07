import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Archive {

    private List<Document> documents = new LinkedList<>();

    public boolean addDocument(Document document) {
        if (!documents.contains(document) && document.getSigner().isPresent()) {
            for (Document doc : documents) {
                if (doc.getId() == document.getId()) {
                    return false;
                }
            }
            documents.add(document);
            return true;
        }
        return false;
    }

    public List<Document> getDocumentsByOwner(Person person) {
        List<Document> answer = new LinkedList<>();
        for (Document doc : documents) {
            if (doc.getOwner().equals(person)) {
                answer.add(doc);
            }
        }
        return answer;
    }

    public List<Document> getDocumentsBySigner(Person person) {
        List<Document> answer = new LinkedList<>();
        for (Document doc : documents) {
            if (doc.getSigner().isPresent() && doc.getSigner().get().equals(person)) {
                answer.add(doc);
            }
        }
        return answer;
    }

    public Optional<Document> getDocumentById(int id) {
        for (Document doc : documents) {
            if (doc.getId() == id) {
                return Optional.of(doc);
            }
        }
        return Optional.empty();
    }

    public List<Document> getDocumentsWithSimilarContent(String stringToLookFor) {
        List<Document> answer = new LinkedList<>();
        for (Document doc : documents) {
            if (doc.getContent().contains(stringToLookFor)) {
                answer.add(doc);
            }
        }
        return answer;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
