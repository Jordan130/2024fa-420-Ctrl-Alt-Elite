package Controller;

import java.awt.Point;
import java.util.Map;
import java.util.Set;

import Model.RelationshipType;
import Model.UmlClass;
import Model.UmlEditorModel;

public class UmlEditor {
    /** The model that holds the classes and relationships for this Uml Editor */
    private UmlEditorModel model;

/*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructor initializes the collections for classes and relationships.
     */
    public UmlEditor(UmlEditorModel model) {
        this.model = model;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                                           CLASS MANAGEMENT METHODS                                             */
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Adds a new class if it doesn't already exist and the name is not null or empty.
     * 
     * @param name The name of the new class.
     * @param position The initial position of the new class.
     * @return {@code true} if the class was added, {@code false} otherwise.
     */
    public boolean addClass(String name, Point position) {
        if (name == null || name.isEmpty() || name.contains(" ")) {
            return false;
        }
        return model.addClass(name, position); // Pass the position to the model
    }

    /**
     * Adds a new class if it doesn't already exist and the name is not null or empty.
     * 
     * @param name The name of the new class.
     * @return {@code true} if the class was added, {@code false} otherwise.
     */
    public boolean addClass(String name) {
        if (name == null || name.isEmpty() || name.contains(" ")) {
            return false;
        }
        return model.addClass(name); 
    }

    /**
     * Deletes a class and all relationships involving that class.
     * 
     * @param name The name of the class to be deleted.
     * @return {@code true} if the class was deleted, {@code false} otherwise.
     */
    public boolean deleteClass(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return model.deleteClass(name);
    }

    /**
     * Rename a class to a new name if it doesn't already exist
     * and the name is not null or empty.
     * 
     * @param oldName The class's old name.
     * @param newName The class's new name.
     * @return {@code true} if the class was renamed, {@code false} otherwise.
     */
    public boolean renameClass(String oldName, String newName) {
        if (oldName == null || oldName.isEmpty() || newName == null || newName.isEmpty() || newName.contains(" ")) {
            return false;
        }
        
        return model.renameClass(oldName, newName);
    }

    /**
     * Retrieves a UML class given a name.
     * 
     * @param name The name of the UML class to retrieve.
     * @return The UML class if found, {@code null} otherwise.
     */
    public UmlClass getClass(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return model.getUmlClass(name);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                                            FIELD MANAGEMENT METHODS                                            */
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Retrieves the fields of a specified UML class by name
     * 
     * @param className The name of the class in which the fields belongs
     * @return A set of fields that belong to a class.
     */
    public Set<String> getFields(String className) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.getFields(); // Assuming getFields() returns a Set<String> of field names
        }
        return null; // Return null if the class does not exist
    }

    public boolean addField(String className, String fieldName) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            boolean result = umlClass.addField(fieldName);
            if (!result) {
                System.out.println("Field '" + fieldName + "' already exists in class '" + className + "'.");
            }
            return result;
        }
        System.out.println("Class '" + className + "' not found.");
        return false;
    }

    /**
     * Delete a field from a class.
     * 
     * @param className The name of the class in which the field will be deleted.
     * @param fieldName The name of the field.
     * @return {@code true} if the field was deleted, {@code false} otherwise.
     */
    public boolean deleteField(String className, String fieldName) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.deleteField(fieldName);
        }
        return false;
    }

    /**
     * Rename a field in a class.
     * 
     * @param className    The name of the class in which the field will be renamed.
     * @param oldFieldName The old name of the field.
     * @param newFieldName The new name of the field.
     * @return {@code true} if the field was renamed, {@code false} otherwise.
     */
    public boolean renameField(String className, String oldFieldName, String newFieldName) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.renameField(oldFieldName, newFieldName);
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                                           METHOD MANAGEMENT METHODS                                            */
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Adds a method to a specified class.
     * 
     * @param className  The name of the class in which the method will be added.
     * @param methodName The name of the method.
     * @param paraList   The list of parameters that belong to the method.
     * @param returnType The return type of the method.
     * @return {@code true} if the method was added, {@code false} otherwise.
     */
    public boolean addMethod(String className, String methodName, Map<String, String> paraList, String returnType) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.addMethod(methodName, paraList, returnType);
        }
        return false;
    }

    /**
     * Deletes a method from a specified class.
     * 
     * @param className  The name of the class in which the method will be deleted.
     * @param methodName The name of the method.
     * @param paraList   The list of parameters that belong to the method.
     * @param returnType The return type of the method.
     * @return {@code true} if the method was deleted, {@code false} otherwise.
     */
    public boolean deleteMethod(String className, String methodName, Map<String, String> paraList, String returnType) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.deleteMethod(methodName, paraList, returnType);
        }
        return false;
    }

    /**
     * Renames a method in a specified class.
     * 
     * @param className The name of the class in which the method will be renamed.
     * @param oldName   The old name of the method.
     * @param paraList  The list of parameters that belong to the method.
     * @param returnType The return type of the method.
     * @param newName   The new name of the method.
     * @return {@code true} if the method was renamed, {@code false} otherwise.
     */
    public boolean renameMethod(String className, String oldName, Map<String, String> paraList, String returnType, String newName) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.renameMethod(oldName, paraList, returnType, newName);
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                                        PARAMETER MANAGEMENT METHODS                                            */
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Remove a parameter from a method.
     * 
     * @param className  The name of the class in which the method belongs.
     * @param methodName The name of the method in which the parameter belongs.
     * @param parameters The parameters of the method.
     * @param returnType The return type of the method.
     * @param paraName   The name of the parameter to remove.
     * @return {@code true} if the parameter was removed, {@code false} otherwise.
     */
    public boolean removeParameter(String className, String methodName, Map<String, String> parameters, 
                                    String returnType, String[] parameterPair) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.removeParameter(methodName, parameters, returnType, parameterPair);
        }
        return false;
    }

    /**
     * Replace the list of parameters of a certain method with a new one.
     * 
     * @param className  The name of the class in which the parameters belong.
     * @param methodName The name of the method in which the parameters belong.
     * @param oldParameters The old list of parameters.
     * @param returnType The return type of the method.
     * @param newParameters The new list of parameters for the method.
     * @return {@code true} if the parameters were changed, {@code false} otherwise.
     */
    public boolean changeParameters(String className, String methodName, Map<String, String> oldParameters, 
                                        String returnType, Map<String, String> newParameters) {
        UmlClass umlClass = model.getUmlClass(className);
        if (umlClass != null) {
            return umlClass.changeParameters(methodName, oldParameters, returnType, newParameters);
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*                                      RELATIONSHIP MANAGEMENT METHODS                                           */
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Adds a relationship between two classes, if both exist and are not the same.
     * 
     * @param source      The source of the relationship.
     * @param destination The destination of the relationship.
     * @param type        The type of relationship.
     * @return {@code true} if the relationship was added, {@code false} otherwise.
     */
    public boolean addRelationship(String source, String destination, RelationshipType type) {
        if (model.classExist(source) && model.classExist(destination)) {
            return model.addRelationship(source, destination, type);
        }
        return false; // One or both classes do not exist
    }

    /**
     * Deletes a relationship between two classes.
     * 
     * @param source      The source of the relationship.
     * @param destination The destination of the relationship.
     * @param type        The type of relationship.
     * @return {@code true} if the relationship was deleted, {@code false}
     *         otherwise.
     */
    public boolean deleteRelationship(String source, String destination, RelationshipType type) {
        if (model.classExist(source) && model.classExist(destination)) {
            return model.deleteRelationship(source, destination, type);
        }
        return false;
    }

    /**
     * Changes the type of an existing relationship.
     * 
     * @param source The source entity.
     * @param destination The destination entity.
     * @param newType The new type to change the relationship to.
     * @return {@code true} if the relationship was changed, {@code false} if the relationship could not be changed.
     */
    public boolean changeRelationshipType(String source, String destination, RelationshipType currentType, RelationshipType newType) {
        if (model.classExist(source) && model.classExist(destination)) {
            return model.changeRelationshipType(source, destination, currentType, newType);
        }
        return false;
    }
}

/*----------------------------------------------------------------------------------------------------------------*/