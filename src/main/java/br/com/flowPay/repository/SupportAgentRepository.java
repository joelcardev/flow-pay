package br.com.flowPay.repository;
import br.com.flowPay.enums.agent.AgentStatus;
import br.com.flowPay.enums.request.RequestStatus;
import br.com.flowPay.enums.request.RequestType;
import br.com.flowPay.model.supportAgent.SupportAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupportAgentRepository extends JpaRepository<SupportAgent, Long> {

    List<SupportAgent> findByStatus(AgentStatus status);

    @Query(value = "SELECT sa.* FROM support_agent sa " +
            "LEFT JOIN customer_request r ON sa.id = r.support_agent_id " +
            "WHERE sa.status = :status " +
            "AND sa.request_type = :requestType " +
            "AND (r.status = 'IN_PROGRESS' OR r.id IS NULL) " +
            "GROUP BY sa.id " +
            "HAVING COUNT(r.id) < :maxRequests " +
            "ORDER BY sa.id ASC " +
            "LIMIT 1", nativeQuery = true)
    SupportAgent findFirstAvailableAgent(
            @Param("status") String status,
            @Param("requestType") String requestType,
            @Param("maxRequests") long maxRequests
    );



}