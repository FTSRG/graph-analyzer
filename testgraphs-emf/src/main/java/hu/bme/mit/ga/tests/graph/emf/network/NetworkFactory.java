/**
 */
package hu.bme.mit.ga.tests.graph.emf.network;

import hu.bme.mit.ga.tests.graph.emf.network.impl.NetworkFactoryImpl;
import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see NetworkPackage
 * @generated
 */
public interface NetworkFactory extends EFactory {
        /**
         * The singleton instance of the factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        NetworkFactory eINSTANCE = NetworkFactoryImpl.init();

        /**
         * Returns a new object of class '<em>Node Container</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Node Container</em>'.
         * @generated
         */
        NodeContainer createNodeContainer();

        /**
         * Returns a new object of class '<em>Node</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Node</em>'.
         * @generated
         */
        Node createNode();

        /**
         * Returns the package supported by this factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the package supported by this factory.
         * @generated
         */
        NetworkPackage getNetworkPackage();

} //NetworkFactory
