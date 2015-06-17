import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * TransformCalculator provides some utility routines to translate from
 * the coordinates used by shapes to actual screen coordinates.
 * 
 * @author Barbara Lerner
 * @version Feb 9, 2010
 *
 */
public class TransformCalculator {

	/**
	 * Determine where the corners of a bounding box after rotation and translation
	 * @param unRotatedBounds the bounding box of a shape before being transformed.  This should not be null.
	 * @param horizontalOffset the distance to translate the point horizontally
	 * @param verticalOffset the distance to translate vertically
	 * @param rotation the amount to rotate
	 */
	private static Point2D.Double[] calculateBoundingBoxCorners(Rectangle2D unRotatedBounds,
			double horizontalOffset, double verticalOffset, double rotation) {
		assert unRotatedBounds != null;
		
		Point2D.Double[] pts = new Point2D.Double[4];
		
		pts[0] = transform (new Point2D.Double(unRotatedBounds.getMinX(), unRotatedBounds.getMinY()), horizontalOffset, verticalOffset, rotation);
		pts[1] = transform(new Point2D.Double(unRotatedBounds.getMaxX(), unRotatedBounds.getMinY()), horizontalOffset, verticalOffset, rotation);
		pts[2] = transform(new Point2D.Double(unRotatedBounds.getMaxX(), unRotatedBounds.getMaxY()), horizontalOffset, verticalOffset, rotation);
		pts[3] = transform(new Point2D.Double(unRotatedBounds.getMinX(), unRotatedBounds.getMaxY()), horizontalOffset, verticalOffset, rotation);
		
		return pts;
	}

	/**
	 * Transform a point by applying a translation and rotation.
	 * @param pt the original point.  This should not be null.
	 * @param horizontalOffset the distance to translate the point horizontally
	 * @param verticalOffset the distance to translate the point vertically
	 * @param rotation the rotation to apply, in radians
	 * @return the point after applying the transformation
	 */
	public static Point2D.Double transform (Point2D.Double pt, double horizontalOffset, double verticalOffset, double rotation) {
		assert pt != null;
		return new Point2D.Double(pt.getX() * Math.cos(rotation) - pt.getY() * Math.sin(rotation) + horizontalOffset,
				pt.getX() * Math.sin(rotation) + pt.getY() * Math.cos(rotation) + verticalOffset);
	}

	/**
	 * Find the bounding box for a rotated shape.
	 * @param unRotatedBounds the bounds before rotation and translation
	 * @param horizontalOffset the distance to translate horizontally
	 * @param verticalOffset the distance to translate vertically
	 * @param rotation the amount to rotate, in radians
	 * @return a bounding box that surrounds the rotated rectangle
	 */
	public static Rectangle2D calculateRotatedBounds(
			Rectangle2D unRotatedBounds, double horizontalOffset,
			double verticalOffset, double rotation) {
		Point2D.Double[] rotatedBounds = calculateBoundingBoxCorners(unRotatedBounds, horizontalOffset, verticalOffset, rotation);

		Point2D.Double topLeft = topLeft(rotatedBounds);
		Point2D.Double bottomRight = bottomRight(rotatedBounds);

		// Create a bounding box for the transformed points.
		return new Rectangle2D.Double(topLeft.getX(), topLeft.getY(), bottomRight.getX()-topLeft.getX(), bottomRight.getY()-topLeft.getY());
		
	}

	/**
	 * Find the top left corner of the bounding box
	 * @param bounds the points to include in the bounds.  This should not be null.
	 * @return the top left corner of the bounding box
	 */
	private static Point2D.Double topLeft(Point2D.Double[] bounds) {
		assert bounds != null;
		
		double xMin = bounds[0].getX();
		double yMin = bounds[0].getY();
		
		for (int i = 1; i < bounds.length; i++) {
			if (bounds[i].getX() < xMin) {
				xMin = bounds[i].getX();
			}
			if (bounds[i].getY() < yMin) {
				yMin = bounds[i].getY();
			}
		}
		
		return new Point2D.Double(xMin, yMin);
	}

	/**
	 * Find the bottom right corner of the bounding box
	 * @param bounds the points to include in the bounds.  This should not be null.
	 * @return the bottom right corner of the bounding box
	 */
	private static Point2D.Double bottomRight(Point2D.Double[] bounds) {
		assert bounds != null;
		
		double xMax = bounds[0].getX();
		double yMax = bounds[0].getY();
		
		for (int i = 1; i < bounds.length; i++) {
			if (bounds[i].getX() > xMax) {
				xMax = bounds[i].getX();
			}
			if (bounds[i].getY() > yMax) {
				yMax = bounds[i].getY();
			}
		}
		
		return new Point2D.Double(xMax, yMax);
	}

}
