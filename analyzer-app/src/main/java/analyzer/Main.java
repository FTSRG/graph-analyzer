package analyzer;

import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import hu.bme.mit.ga.metrics.impl.simple.Density;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes;
import hu.bme.mit.ga.metrics.impl.typed.EdgeOverlap;
import hu.bme.mit.ga.metrics.impl.typed.EdgeTypedConnectivity;
import hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.NodeActivity;
import hu.bme.mit.ga.metrics.impl.typed.NodeTypedConnectivity;
import hu.bme.mit.ga.metrics.impl.typed.NumberOfTypedEdges;
import hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity;
import hu.bme.mit.ga.metrics.impl.typed.TypedActivity;
import hu.bme.mit.ga.metrics.impl.typed.TypedClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.TypedClusteringCoefficientDef1;
import hu.bme.mit.ga.metrics.impl.typed.TypedClusteringCoefficientDef2;
import hu.bme.mit.ga.metrics.impl.typed.TypedClusteringCoefficientDef3;
import hu.bme.mit.ga.metrics.impl.typed.TypedDegree;
import hu.bme.mit.ga.metrics.impl.typed.TypedDegreeEntropy;
import org.supercsv.cellprocessor.constraint.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String graph = args[0];
        final String metrics = graph + ".tsv";
        final String nodes = graph + "-nodes.csv";
        final String edges = graph + "-edges.csv";

        final String[] header = new String[]{"Category", "Instance", "Index", "Value"};
        List<AbstractGraphMetric> modelMetrics = new ArrayList<>();

        // the adapter represents a bridge between model and metrics
        CsvGraphAdapter adapter = new CsvGraphAdapter(new NotNull());

        // adapter must be initialized, this will create an index which is necessary during the evaluation
        adapter.init(nodes, edges);
        System.out.println("initialized");

        // calculate metrics
//        System.out.println("degree");
//        Degrees dd = new Degrees();
//        dd.evaluate(adapter);
//        modelMetrics.add(dd);

        System.out.println("density");
        Density d = new Density();
        d.evaluate(adapter);
        modelMetrics.add(d);

        System.out.println("typed activity");
        TypedActivity typedActivity = new TypedActivity();
        typedActivity.evaluate(adapter);
        modelMetrics.add(typedActivity);

        System.out.println("typed degree");
        TypedDegree typedDegree = new TypedDegree();
        typedDegree.evaluate(adapter);
        modelMetrics.add(typedDegree);

        System.out.println("typed clustering coefficient 1");
        TypedClusteringCoefficient tc1 = new TypedClusteringCoefficientDef1();
        tc1.evaluate(adapter);
        modelMetrics.add(tc1);

        System.out.println("typed clustering coefficient 2");
        TypedClusteringCoefficient tc2 = new TypedClusteringCoefficientDef2();
        tc2.evaluate(adapter);
        modelMetrics.add(tc2);

        System.out.println("typed clustering coefficient 3");
        TypedClusteringCoefficient tc3 = new TypedClusteringCoefficientDef3();
        tc3.evaluate(adapter);
        modelMetrics.add(tc3);

        System.out.println("typed degree entropy");
        TypedDegreeEntropy dde = new TypedDegreeEntropy();
        dde.evaluate(adapter);
        modelMetrics.add(dde);

        System.out.println("node typed connectivity");
        NodeTypedConnectivity nodeTypedConnectivity = new NodeTypedConnectivity();
        nodeTypedConnectivity.evaluate(adapter);
        modelMetrics.add(nodeTypedConnectivity);

        System.out.println("edge typed connectivity");
        EdgeTypedConnectivity edgeTypedConnectivity = new EdgeTypedConnectivity();
        edgeTypedConnectivity.evaluate(adapter);
        modelMetrics.add(edgeTypedConnectivity);

        System.out.println("edge overlap");
        EdgeOverlap eo = new EdgeOverlap();
        eo.evaluate(adapter);
        modelMetrics.add(eo);

        MultiplexParticipationCoefficient mpc = new MultiplexParticipationCoefficient();
        System.out.println("mpc");
        mpc.evaluate(adapter);
        modelMetrics.add(mpc);

        System.out.println("node activity");
        NodeActivity nodeActivity = new NodeActivity();
        nodeActivity.evaluate(adapter);
        modelMetrics.add(nodeActivity);

        System.out.println("number of edges");
        NumberOfEdges numberOfEdges = new NumberOfEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfEdges);

        System.out.println("number of nodes");
        NumberOfNodes numberOfNodes = new NumberOfNodes();
        numberOfNodes.evaluate(adapter);
        modelMetrics.add(numberOfNodes);

        System.out.println("number of typed edges");
        NumberOfTypedEdges numberOfTypedEdges = new NumberOfTypedEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfTypedEdges);

        System.out.println("pairwise multiplexity");
        PairwiseMultiplexity pm = new PairwiseMultiplexity();
        pm.evaluate(adapter);
        modelMetrics.add(pm);

        Analyzer.writeToTsv(modelMetrics, header, metrics);
    }

}
