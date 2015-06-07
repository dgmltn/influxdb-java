package org.influxdb.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.impl.TimeUtil;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * {Purpose of This Type}
 *
 * {Other Notes Relating to This Type (Optional)}
 *
 * @author stefan
 *
 */
public class BatchPoints {
	private String database;
	private String retentionPolicy;
	private Map<String, String> tags;
	private Long time;
	private String precision;
	private List<Point> points;

	// private static final String TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'";
	// final SimpleDateFormat isoFormatter = new SimpleDateFormat(TIMESTAMP_DATE_FORMAT, LOCALE);
	// isoFormatter.setTimeZone(TimeZone.getTimeZone(UTC));
	// isoFormatter.format(new Date())

	BatchPoints() {

	}

	public static class Builder {
		private final String database;
		private String retentionPolicy;
		private final Map<String, String> tags = Maps.newHashMap();
		private Long time;
		private String precision;
		private final List<Point> points = Lists.newArrayList();

		/**
		 * @param database
		 */
		public Builder(final String database) {
			super();
			this.database = database;
		}

		public Builder retentionPolicy(final String policy) {
			this.retentionPolicy = policy;
			return this;
		}

		/**
		 * Add a tag to this set of points.
		 *
		 * @param tagName
		 *            the tag name
		 * @param value
		 *            the tag value
		 * @return the Builder instance.
		 */
		public Builder tag(final String tagName, final String value) {
			this.tags.put(tagName, value);
			return this;
		}

		/**
		 * Add a time to this set of points.
		 *
		 * @param precisionToSet
		 * @param timeToSet
		 * @return the Builder instance.
		 */
		public Builder time(final long timeToSet, final TimeUnit precisionToSet) {
			this.precision = TimeUtil.toTimePrecision(precisionToSet);
			this.time = timeToSet;
			return this;
		}

		/**
		 * Add a Point to this set of points.
		 *
		 * @param pointToAdd
		 * @return the Builder instance
		 */
		public Builder point(final Point pointToAdd) {
			this.points.add(pointToAdd);
			return this;
		}

		/**
		 * Add a set of Points to this set of points.
		 *
		 * @param pointsToAdd
		 * @return the Builder instance
		 */
		public Builder points(final Point... pointsToAdd) {
			this.points.addAll(Arrays.asList(pointsToAdd));
			return this;
		}

		public BatchPoints build() {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(this.database), "Database must not be null or empty.");
			BatchPoints batchPoints = new BatchPoints();
			batchPoints.setDatabase(this.database);
			batchPoints.setPoints(this.points);
			batchPoints.setPrecision(this.precision);
			batchPoints.setRetentionPolicy(this.retentionPolicy);
			batchPoints.setTags(this.tags);
			batchPoints.setTime(this.time);
			return batchPoints;
		}
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return this.database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	void setDatabase(final String database) {
		this.database = database;
	}

	/**
	 * @return the retentionPolicy
	 */
	public String getRetentionPolicy() {
		return this.retentionPolicy;
	}

	/**
	 * @param retentionPolicy
	 *            the retentionPolicy to set
	 */
	void setRetentionPolicy(final String retentionPolicy) {
		this.retentionPolicy = retentionPolicy;
	}

	/**
	 * @return the points
	 */
	public List<Point> getPoints() {
		return this.points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	void setPoints(final List<Point> points) {
		this.points = points;
	}

	public BatchPoints point(final Point point) {
		this.points.add(point);
		return this;
	}

	/**
	 * @return the tags
	 */
	public Map<String, String> getTags() {
		return this.tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	void setTags(final Map<String, String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return this.time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	void setTime(final Long time) {
		this.time = time;
	}

	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return this.precision;
	}

	/**
	 * @param precision
	 *            the precision to set
	 */
	void setPrecision(final String precision) {
		this.precision = precision;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BatchPoints [database=");
		builder.append(this.database);
		builder.append(", retentionPolicy=");
		builder.append(this.retentionPolicy);
		builder.append(", tags=");
		builder.append(this.tags);
		builder.append(", time=");
		builder.append(this.time);
		builder.append(", precision=");
		builder.append(this.precision);
		builder.append(", points=");
		builder.append(this.points);
		builder.append("]");
		return builder.toString();
	}

}