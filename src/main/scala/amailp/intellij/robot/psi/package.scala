package amailp.intellij.robot


import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi._
import com.intellij.psi.util.PsiTreeUtil

package object psi {
  case class Ellipsis(node: ASTNode) extends ASTWrapperPsiElement(node)
  case class Settings(node: ASTNode) extends ASTWrapperPsiElement(node)
  case class SettingName(node: ASTNode) extends ASTWrapperPsiElement(node)
  case class TestCaseName(node: ASTNode) extends ASTWrapperPsiElement(node)
  case class KeywordName (node: ASTNode) extends ASTWrapperPsiElement(node)
  case class KeywordDefinition (node: ASTNode) extends ASTWrapperPsiElement(node) {
    def name: String = getNode.findChildByType(parser.KeywordName).getText
    def matches(string: String) = name equalsIgnoreCase string
  }

  abstract class RobotReferenceBase[T <: PsiElement](element: T) extends PsiReferenceBase[T](element){
    def currentRobotFile = PsiTreeUtil.getParentOfType(getElement, classOf[RobotPsiFile])
    def currentFile = currentRobotFile.getVirtualFile
    def currentDirectory = currentFile.getParent
    def psiManager = PsiManager.getInstance(getElement.getProject)
  }
}
